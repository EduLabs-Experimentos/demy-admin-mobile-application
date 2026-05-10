package com.nistra.demy.admins.features.billing.presentation.state

import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.features.billing.domain.model.BillingAccount

data class BillingAccountDetailsUiState (
    val isLoading: Boolean = false,
    val billingAccount: BillingAccount? = null,
    val error: String? = null,
    val snackbarMessage: SnackbarMessage? = null,
    val isAddInvoiceDialogVisible: Boolean = false
)
