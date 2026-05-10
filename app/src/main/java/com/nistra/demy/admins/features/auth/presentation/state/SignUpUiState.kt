package com.nistra.demy.admins.features.auth.presentation.state

import com.nistra.demy.admins.core.common.BaseUiState

data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null
) : BaseUiState
