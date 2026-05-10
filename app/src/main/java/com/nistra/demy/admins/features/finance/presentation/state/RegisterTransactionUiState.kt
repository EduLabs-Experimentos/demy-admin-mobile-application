package com.nistra.demy.admins.features.finance.presentation.state

import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.features.finance.domain.model.Transaction

data class RegisterTransactionUiState(
    val isLoading: Boolean = false,
    val transactions: List<Transaction> = emptyList(),
    val filteredTransactions: List<Transaction> = emptyList(),
    val isLoadingTransactions: Boolean = false,
    val snackbarMessage: SnackbarMessage? = null
)

