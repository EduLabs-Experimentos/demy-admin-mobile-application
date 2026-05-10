package com.nistra.demy.admins.features.students.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.students.domain.model.Student
import com.nistra.demy.admins.features.students.domain.usecase.CreateStudentUseCase
import com.nistra.demy.admins.features.students.domain.usecase.DeleteStudentUseCase
import com.nistra.demy.admins.features.students.domain.usecase.GetAllStudentsUseCase
import com.nistra.demy.admins.features.students.domain.usecase.UpdateStudentUseCase
import com.nistra.demy.admins.features.students.presentation.model.StudentFormData
import com.nistra.demy.admins.features.students.presentation.state.StudentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class StudentsViewModel @Inject constructor(
    private val getAllStudentsUseCase: GetAllStudentsUseCase,
    private val createStudentUseCase: CreateStudentUseCase,
    private val updateStudentUseCase: UpdateStudentUseCase,
    private val deleteStudentUseCase: DeleteStudentUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(StudentUiState())
    val uiState: StateFlow<StudentUiState> = _uiState.asStateFlow()

    private val _formData = MutableStateFlow(StudentFormData())
    val formData: StateFlow<StudentFormData> = _formData.asStateFlow()

    private var allStudentsCache: List<Student> = emptyList()

    init {
        loadStudents()
    }

    fun onStudentFormChange(updated: StudentFormData) {
        _formData.value = updated
        _uiState.update { it.copy(isFormSuccess = false, errorMessage = null) }
    }

    fun onSaveStudentClick() {
        handleSaveStudent()
    }

    fun onStudentSelectedForEdit(student: Student) {
        _uiState.update { it.copy(studentToEdit = student, errorMessage = null) }
        _formData.value = StudentFormData(
            firstName = student.firstName,
            lastName = student.lastName,
            dni = student.dni,
            emailAddress = student.emailAddress,
            sex = student.sex,
            birthDate = student.birthDate.toString(),
            street = student.street,
            district = student.district,
            province = student.province,
            department = student.department,
            countryCode = student.countryCode,
            phone = student.phone
        )
    }

    fun onClearFormClick() {
        _uiState.update { it.copy(studentToEdit = null, isFormSuccess = false, errorMessage = null) }
        _formData.value = StudentFormData()
    }

    fun onDeleteStudentClick(student: Student) {
        handleDeleteStudent(student)
    }

    fun searchStudents(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        val filteredList = filterStudents(allStudentsCache, query)
        _uiState.update { it.copy(students = filteredList) }
    }

    private fun loadStudents() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            getAllStudentsUseCase()
                .onSuccess { fetchedStudents ->
                    allStudentsCache = fetchedStudents
                    val filteredList = filterStudents(allStudentsCache, _uiState.value.searchQuery)
                    _uiState.update { it.copy(students = filteredList, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = e.message ?: "Error al cargar estudiantes."
                        )
                    }
                }
        }
    }

    private fun handleSaveStudent() {
        val existingId = _uiState.value.studentToEdit?.id ?: 0L
        val data = _formData.value

        val studentToSave = Student(
            id = existingId,
            firstName = data.firstName.trim(),
            lastName = data.lastName.trim(),
            dni = data.dni.trim(),
            emailAddress = data.emailAddress.trim(),
            sex = data.sex.trim(),
            birthDate = LocalDate.parse(data.birthDate.trim()),
            street = data.street.trim(),
            district = data.district.trim(),
            province = data.province.trim(),
            department = data.department.trim(),
            countryCode = data.countryCode.trim(),
            phone = data.phone.trim()
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = if (studentToSave.id == 0L) {
                createStudentUseCase(studentToSave)
            } else {
                updateStudentUseCase(studentToSave)
            }

            result.onSuccess {
                    onClearFormClick()
                    loadStudents()
                    _uiState.update { it.copy(isFormSuccess = true, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = e.message ?: "Error al guardar el estudiante."
                        )
                    }
                }
        }
    }

    private fun handleDeleteStudent(student: Student) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            deleteStudentUseCase(student.id)
                .onSuccess {
                    loadStudents()
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = e.message ?: "Error al eliminar el estudiante."
                        )
                    }
                }
        }
    }

    private fun filterStudents(list: List<Student>, query: String): List<Student> {
        return if (query.isBlank()) {
            list
        } else {
            list.filter { student ->
                student.firstName.contains(query, ignoreCase = true) ||
                    student.lastName.contains(query, ignoreCase = true) ||
                    student.dni.contains(query, ignoreCase = true)
            }
        }
    }
}
