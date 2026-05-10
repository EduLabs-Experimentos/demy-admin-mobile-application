package com.nistra.demy.admins.features.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.core.storage.SessionPreferences
import com.nistra.demy.admins.features.auth.domain.usecase.CompleteAccountUseCase
import com.nistra.demy.admins.features.auth.presentation.model.CompleteAccountFormData
import com.nistra.demy.admins.features.auth.presentation.state.CompleteAccountUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompleteAccountViewModel @Inject constructor(
    private val completeAccountUseCase: CompleteAccountUseCase,
    private val sessionPreferences: SessionPreferences
) : ViewModel() {
    private val _uiState = MutableStateFlow(CompleteAccountUiState())
    val uiState: StateFlow<CompleteAccountUiState> = _uiState

    private val _formData = MutableStateFlow(CompleteAccountFormData())
    val formData: StateFlow<CompleteAccountFormData> = _formData

    fun onFirstNameChange(value: String) {
        _formData.value = _formData.value.copy(firstName = value)
    }

    fun onLastNameChange(value: String) {
        _formData.value = _formData.value.copy(lastName = value)
    }

    fun onCountryCodeChange(value: String) {
        _formData.value = _formData.value.copy(countryCode = value)
    }

    fun onPhoneNumberChange(value: String) {
        _formData.value = _formData.value.copy(phoneNumber = value)
    }

    fun onDniNumberChange(value: String) {
        _formData.value = _formData.value.copy(dniNumber = value)
    }

    fun completeAccount() {
        val data = _formData.value

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            val userId = sessionPreferences.userId.firstOrNull()?.toLongOrNull()
            if (userId == null) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "User ID not found. Please verify your account again."
                )
                return@launch
            }

            completeAccountUseCase(
                data.firstName,
                data.lastName,
                data.countryCode,
                data.phoneNumber,
                data.dniNumber,
                userId
            )
                .onSuccess { adminId ->
                    sessionPreferences.saveAdministratorId(adminId)
                    _uiState.value = _uiState.value.copy(isLoading = false, isSuccess = true)
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = exception.message)
                }
        }
    }
}
