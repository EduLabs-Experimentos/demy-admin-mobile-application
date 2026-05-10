package com.nistra.demy.admins.features.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.core.storage.SessionPreferences
import com.nistra.demy.admins.features.auth.domain.usecase.SetUpAcademyUseCase
import com.nistra.demy.admins.features.auth.presentation.model.AcademySetupFormData
import com.nistra.demy.admins.features.auth.presentation.state.AcademySetupUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcademySetupViewModel @Inject constructor(
    private val setUpAcademyUseCase: SetUpAcademyUseCase,
    private val sessionPreferences: SessionPreferences
) : ViewModel() {
    private val _uiState = MutableStateFlow(AcademySetupUiState())
    val uiState: StateFlow<AcademySetupUiState> = _uiState

    private val _formData = MutableStateFlow(AcademySetupFormData())
    val formData: StateFlow<AcademySetupFormData> = _formData

    fun onFieldChange(updated: AcademySetupFormData) {
        _formData.value = updated
    }

    fun setUpAcademy() {
        val data = _formData.value

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            val adminId = sessionPreferences.administratorId.firstOrNull()?.toLongOrNull()
            if (adminId == null) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Administrator ID not found. Please complete your account first."
                )
                return@launch
            }

            setUpAcademyUseCase(
                data.academyName,
                data.academyDescription,
                data.street,
                data.district,
                data.province,
                data.department,
                data.emailAddress,
                data.countryCode,
                data.phone,
                data.ruc,
                adminId
            )
            .onSuccess { academyId ->
                sessionPreferences.saveAcademyId(academyId)
                _uiState.value = _uiState.value.copy(isLoading = false, isSuccess = true)
            }
            .onFailure { exception ->
                _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = exception.message)
            }
        }
    }
}
