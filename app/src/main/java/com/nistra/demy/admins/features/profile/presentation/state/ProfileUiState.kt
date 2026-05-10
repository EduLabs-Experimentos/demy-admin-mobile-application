package com.nistra.demy.admins.features.profile.presentation.state

import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.features.profile.data.remote.ProfileStats

sealed interface ProfileUiState {
    data object Loading : ProfileUiState
    data class Success(val stats: ProfileStats) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}
