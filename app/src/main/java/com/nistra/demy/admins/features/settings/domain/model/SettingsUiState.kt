package com.nistra.demy.admins.features.settings.domain.model

data class SettingsUiState(
    val darkTheme: Boolean = false,
    val highContrast: Boolean = false,
    val notificationsEnabled: Boolean = true,
    val marketingEnabled: Boolean = false
)
