package com.nistra.demy.admins.features.help.presentation.model

sealed class HelpUiState {
    data object Loading : HelpUiState()
    data class Success(
        val email: String,
        val phone: String,
        val schedule: String
    ) : HelpUiState()
    data class Error(val message: String) : HelpUiState()
}
