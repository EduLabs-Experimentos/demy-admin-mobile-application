package com.nistra.demy.admins.features.auth.presentation.state

import com.nistra.demy.admins.core.common.BaseUiState

data class VerifyAccountUiState(
    val isSuccess: Boolean = false,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null
) : BaseUiState
