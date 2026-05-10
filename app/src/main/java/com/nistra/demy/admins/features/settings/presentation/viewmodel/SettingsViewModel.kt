package com.nistra.demy.admins.features.settings.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.settings.domain.model.SettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class SettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState

    fun toggleDarkTheme(enabled: Boolean) = update { it.copy(darkTheme = enabled) }

    fun toggleHighContrast(enabled: Boolean) = update { it.copy(highContrast = enabled) }

    fun toggleNotifications(enabled: Boolean) = update { it.copy(notificationsEnabled = enabled) }

    fun toggleMarketing(enabled: Boolean) = update { it.copy(marketingEnabled = enabled) }

    private fun update(transform: (SettingsUiState) -> SettingsUiState) {
        viewModelScope.launch { _uiState.value = transform(_uiState.value) }
    }
}
