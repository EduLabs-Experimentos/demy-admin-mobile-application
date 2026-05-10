package com.nistra.demy.admins.features.billing.presentation.state

import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.features.billing.domain.model.BillingAccount

data class RegisterBillingAccountUiState(
    val isLoading: Boolean = false,
    val billingAccounts: List<BillingAccount> = emptyList(),
    val filteredBillingAccounts: List<BillingAccount> = emptyList(),
    val isLoadingBillingAccounts: Boolean = false,
    val snackbarMessage: SnackbarMessage? = null
)
