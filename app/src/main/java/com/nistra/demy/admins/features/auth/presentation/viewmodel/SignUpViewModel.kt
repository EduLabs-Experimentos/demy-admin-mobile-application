package com.nistra.demy.admins.features.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.BuildConfig
import com.nistra.demy.admins.features.auth.domain.usecase.SignUpUseCase
import com.nistra.demy.admins.features.auth.presentation.state.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState

    fun onEmailChange(value: String) {
        _uiState.value = _uiState.value.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun signUp(onSignUpSuccess: (String) -> Unit) {
        val fixedRole = listOf(BuildConfig.APP_ROLE)
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            signUpUseCase(_uiState.value.email, _uiState.value.password, fixedRole)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    onSignUpSuccess(_uiState.value.email)
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = exception.message)
                }
        }
    }
}
