package com.nistra.demy.admins.features.periods.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.periods.domain.model.AcademicPeriod
import com.nistra.demy.admins.features.periods.domain.repository.AcademicPeriodRepository
import com.nistra.demy.admins.features.periods.domain.usecase.CreatePeriodUseCase
import com.nistra.demy.admins.features.periods.domain.usecase.DeletePeriodUseCase
import com.nistra.demy.admins.features.periods.domain.usecase.GetAllPeriodsUseCase
import com.nistra.demy.admins.features.periods.domain.usecase.UpdatePeriodUseCase
import com.nistra.demy.admins.features.periods.presentation.model.AcademicPeriodFormData
import com.nistra.demy.admins.features.periods.presentation.state.AcademicPeriodUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AcademicPeriodsViewModel @Inject constructor(
    private val getAllPeriodsUseCase: GetAllPeriodsUseCase,
    private val createPeriodUseCase: CreatePeriodUseCase,
    private val updatePeriodUseCase: UpdatePeriodUseCase,
    private val deletePeriodUseCase: DeletePeriodUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AcademicPeriodUiState())
    val uiState: StateFlow<AcademicPeriodUiState> = _uiState.asStateFlow()

    private val _formData = MutableStateFlow(AcademicPeriodFormData())
    val formData: StateFlow<AcademicPeriodFormData> = _formData.asStateFlow()

    private var allPeriodsCache: List<AcademicPeriod> = emptyList()

    init {
        loadPeriods()
    }

    fun onPeriodFormChange(updated: AcademicPeriodFormData) {
        _formData.value = updated
        _uiState.update { it.copy(isFormSuccess = false, errorMessage = null) }
    }

    fun onSavePeriodClick() {
        handleSavePeriod()
    }

    fun onPeriodSelectedForEdit(period: AcademicPeriod) {
        _uiState.update { it.copy(academicPeriodToEdit = period, errorMessage = null) }
        _formData.value = AcademicPeriodFormData(
            name = period.periodName,
            startDate = period.startDate.toString(),
            endDate = period.endDate.toString(),
            isActive = period.isActive
        )
    }

    fun onClearFormClick() {
        _uiState.update { it.copy(academicPeriodToEdit = null, isFormSuccess = false, errorMessage = null) }
        _formData.value = AcademicPeriodFormData()
    }

    fun onDeletePeriodClick(period: AcademicPeriod) {
        handleDeletePeriod(period)
    }

    fun searchPeriods(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        val filteredList = filterPeriods(allPeriodsCache, query)
        _uiState.update { it.copy(academicPeriods = filteredList) }
    }

    private fun loadPeriods() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            getAllPeriodsUseCase().
                onSuccess { fetchedPeriods ->
                    allPeriodsCache = fetchedPeriods
                    val filteredList = filterPeriods(allPeriodsCache, _uiState.value.searchQuery)
                    _uiState.update { it.copy(academicPeriods = filteredList, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al cargar periodos.") }
                }
        }
    }


    private fun handleSavePeriod() {
        val existingId = _uiState.value.academicPeriodToEdit?.id ?: 0L
        val data = _formData.value

        val academicPeriodToSave = AcademicPeriod(
            id = existingId,
            periodName = data.name.trim(),
            startDate = LocalDate.parse(data.startDate.trim()),
            endDate = LocalDate.parse(data.endDate.trim()),
            isActive = data.isActive
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = if (academicPeriodToSave.id == 0L) {
                createPeriodUseCase(academicPeriodToSave)
            } else {
                updatePeriodUseCase(academicPeriodToSave)

            }

            result
                .onSuccess {
                    onClearFormClick()
                    loadPeriods()
                    _uiState.update { it.copy(isFormSuccess = true, errorMessage = null) }

            }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al guardar el período.") }
                }



        }
    }

    private fun handleDeletePeriod(period: AcademicPeriod) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            deletePeriodUseCase(period.id)
                .onSuccess {
                    loadPeriods()
                }
                .onFailure {e ->
                    _uiState.update { it.copy(isLoading = true, errorMessage = e.message ?: "Error al eliminar el periodo") }
                }
        }
    }

    private fun filterPeriods(list: List<AcademicPeriod>, query: String): List<AcademicPeriod> {
        return if (query.isBlank()) {
            list
        } else {
            list.filter { period ->
                period.periodName.contains(query, ignoreCase = true)
            }
        }
    }
}
