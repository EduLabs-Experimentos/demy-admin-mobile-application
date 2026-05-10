package com.nistra.demy.admins.features.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.core.storage.SessionPreferences
import com.nistra.demy.admins.features.auth.domain.usecase.SignInUseCase
import com.nistra.demy.admins.features.auth.presentation.state.SignInUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val sessionPreferences: SessionPreferences
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState

    fun onUsernameChange(value: String) {
        updateState { it.copy(username = value) }
    }

    fun onPasswordChange(value: String) {
        updateState { it.copy(password = value) }
    }

    fun signIn(onLoggedIn: () -> Unit) {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true, errorMessage = null) }

            signInUseCase(_uiState.value.username, _uiState.value.password)
                .onSuccess { userSession ->
                    sessionPreferences.saveUserId(userSession.id)
                    userSession.token?.let { sessionPreferences.saveToken(it) }

                    updateState { it.copy(isLoading = false) }
                    onLoggedIn()
                }
                .onFailure { e ->
                    updateState { it.copy(isLoading = false, errorMessage = e.message) }
                }
        }
    }

    private fun updateState(block: (SignInUiState) -> SignInUiState) {
        _uiState.value = block(_uiState.value)
    }
}
