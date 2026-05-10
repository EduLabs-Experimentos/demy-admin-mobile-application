package com.nistra.demy.admins.features.invoicing.presentation.state

import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.features.invoicing.domain.model.Invoice

data class InvoicesUiState(
    val isLoading: Boolean = false,
    val invoices: List<Invoice> = emptyList(),
    val snackbarMessage: SnackbarMessage? = null
)
