package com.nistra.demy.admins.features.enrollments.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.enrollments.domain.model.Enrollment
import com.nistra.demy.admins.features.enrollments.domain.model.EnrollmentStatus
import com.nistra.demy.admins.features.enrollments.domain.model.PaymentStatus
import com.nistra.demy.admins.features.enrollments.domain.usecase.CreateEnrollmentUseCase
import com.nistra.demy.admins.features.enrollments.domain.usecase.DeleteEnrollmentUseCase
import com.nistra.demy.admins.features.enrollments.domain.usecase.GetAllEnrollmentsUseCase
import com.nistra.demy.admins.features.enrollments.domain.usecase.UpdateEnrollmentUseCase
import com.nistra.demy.admins.features.enrollments.presentation.model.EnrollmentFormData
import com.nistra.demy.admins.features.enrollments.presentation.state.EnrollmentUiState
import com.nistra.demy.admins.features.periods.domain.usecase.GetAllPeriodsUseCase
import com.nistra.demy.admins.features.schedules.domain.usecase.GetAllSchedulesUseCase
import com.nistra.demy.admins.features.students.domain.usecase.GetAllStudentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnrollmentsViewModel @Inject constructor(
    private val getAllEnrollmentsUseCase: GetAllEnrollmentsUseCase,
    private val createEnrollmentUseCase: CreateEnrollmentUseCase,
    private val updateEnrollmentUseCase: UpdateEnrollmentUseCase,
    private val deleteEnrollmentUseCase: DeleteEnrollmentUseCase,
    private val getAllStudentsUseCase: GetAllStudentsUseCase,
    private val getAllPeriodsUseCase: GetAllPeriodsUseCase,
    private val getAllSchedulesUseCase: GetAllSchedulesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(EnrollmentUiState())
    val uiState: StateFlow<EnrollmentUiState> = _uiState.asStateFlow()

    private val _formData = MutableStateFlow(EnrollmentFormData())
    val formData: StateFlow<EnrollmentFormData> = _formData.asStateFlow()

    private var allEnrollmentsCache: List<Enrollment> = emptyList()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val studentsResult = getAllStudentsUseCase()
                val periodsResult = getAllPeriodsUseCase()
                val schedulesResult = getAllSchedulesUseCase()
                val enrollmentsResult = getAllEnrollmentsUseCase()

                if (studentsResult.isSuccess && periodsResult.isSuccess && schedulesResult.isSuccess && enrollmentsResult.isSuccess) {
                    val students = studentsResult.getOrThrow()
                    val periods = periodsResult.getOrThrow()
                    val schedules = schedulesResult.getOrThrow()
                    val enrollments = enrollmentsResult.getOrThrow()

                    // 🔧 Enriquecer
                    val enriched = enrollments.map { enrollment ->
                        val studentName = students.find { it.id == enrollment.studentId }?.let { "${it.firstName} ${it.lastName}" } ?: "-"
                        val periodName = periods.find { it.id == enrollment.periodId }?.periodName ?: "-"
                        val scheduleName = schedules.find { it.id == enrollment.scheduleId }?.name ?: "-"

                        enrollment.copy(
                            studentName = studentName,
                            periodName = periodName,
                            scheduleName = scheduleName
                        )
                    }

                    allEnrollmentsCache = enriched
                    _uiState.update {
                        it.copy(
                            enrollments = enriched,
                            availableStudents = students,
                            availablePeriods = periods,
                            availableSchedules = schedules,
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }

    fun onEnrollmentFormChange(updated: EnrollmentFormData) {
        _formData.value = updated
        _uiState.update { it.copy(isFormSuccess = false, errorMessage = null) }
    }

    fun onSaveEnrollmentClick() {
        handleSaveEnrollment()
    }

    fun onEnrollmentSelectedForEdit(enrollment: Enrollment) {
        _uiState.update { it.copy(enrollmentToEdit = enrollment, errorMessage = null) }
        _formData.value = EnrollmentFormData(
            studentId = enrollment.studentId,
            periodId = enrollment.periodId,
            scheduleId = enrollment.scheduleId,
            amount = enrollment.amount,
            currency = enrollment.currency,
            paymentStatus = enrollment.paymentStatus.name,
            enrollmentStatus = enrollment.enrollmentStatus?.name ?: "ACTIVE"
        )
    }

    fun onClearFormClick() {
        _uiState.update { it.copy(enrollmentToEdit = null, isFormSuccess = false, errorMessage = null) }
        _formData.value = EnrollmentFormData()
    }

    fun onDeleteEnrollmentClick(enrollment: Enrollment) {
        handleDeleteEnrollment(enrollment)
    }

    fun searchEnrollments(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        val filteredList = filterEnrollments(allEnrollmentsCache, query)
        _uiState.update { it.copy(enrollments = filteredList) }
    }

    private fun loadEnrollments() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            getAllEnrollmentsUseCase()
                .onSuccess { fetchedEnrollments ->
                    allEnrollmentsCache = fetchedEnrollments
                    val filteredList = filterEnrollments(allEnrollmentsCache, _uiState.value.searchQuery)
                    _uiState.update { it.copy(enrollments = filteredList, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al cargar matrículas.") }
                }
        }
    }

    private fun handleSaveEnrollment() {
        val existingId = _uiState.value.enrollmentToEdit?.id ?: 0L
        val data = _formData.value

        val enrollmentToSave = if (existingId == 0L) {
            Enrollment(
                id = existingId,
                studentId = data.studentId!!,
                periodId = data.periodId!!,
                scheduleId = data.scheduleId!!,
                amount = data.amount,
                currency = data.currency,
                paymentStatus = PaymentStatus.valueOf(data.paymentStatus.uppercase())
            )
        } else {
            Enrollment(
                id = existingId,
                studentId = data.studentId!!,
                periodId = data.periodId!!,
                scheduleId = data.scheduleId!!,
                amount = data.amount,
                currency = data.currency,
                paymentStatus = PaymentStatus.valueOf(data.paymentStatus.uppercase()),
                enrollmentStatus = EnrollmentStatus.valueOf(data.enrollmentStatus.uppercase())
            )
        }


        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = if (enrollmentToSave.id == 0L) {
                createEnrollmentUseCase(enrollmentToSave)
            } else {
                updateEnrollmentUseCase(enrollmentToSave)
            }

            result
                .onSuccess {
                    onClearFormClick()
                    loadData()
                    _uiState.update { it.copy(isFormSuccess = true, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al guardar la matrícula.") }
                }
        }
    }

    private fun handleDeleteEnrollment(enrollment: Enrollment) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            deleteEnrollmentUseCase(enrollment.id)
                .onSuccess {
                    loadEnrollments()
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al eliminar la matrícula.") }
                }
        }
    }

    private fun filterEnrollments(list: List<Enrollment>, query: String): List<Enrollment> {
        return if (query.isBlank()) {
            list
        } else {
            list.filter { enrollment ->
                enrollment.studentName.contains(query, ignoreCase = true)}
        }
    }
}
