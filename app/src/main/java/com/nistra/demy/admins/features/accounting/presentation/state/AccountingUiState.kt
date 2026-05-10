package com.nistra.demy.admins.features.accounting.presentation.state

import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.features.accounting.domain.model.Transaction

/**
 * UI state for the accounting transactions list screen.
 *
 * @property transactions List of all transactions.
 * @property filteredTransactions List of transactions after applying search filter.
 * @property searchQuery Current search query.
 * @property isLoading Whether data is being loaded.
 * @property errorMessage Error message to display, if any.
 * @property isEditDialogOpen Whether the edit dialog is open.
 * @property transactionBeingEdited The transaction currently being edited.
 * @property isSavingEdit Whether a save operation is in progress.
 * @property isDeleteDialogOpen Whether the delete confirmation dialog is open.
 * @property transactionToDelete The ID of the transaction to delete.
 * @property isDeletingTransaction Whether a delete operation is in progress.
 * @property isExportPdfDialogOpen Whether the PDF export confirmation dialog is open.
 * @property isExportExcelDialogOpen Whether the Excel export confirmation dialog is open.
 * @property isExportingPdf Whether PDF export is in progress.
 * @property isExportingExcel Whether Excel export is in progress.
 * @property snackbarMessage Message to display in snackbar.
 * @author Salim Ramirez
 */
data class AccountingUiState(
    val transactions: List<Transaction> = emptyList(),
    val filteredTransactions: List<Transaction> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isEditDialogOpen: Boolean = false,
    val transactionBeingEdited: Transaction? = null,
    val isSavingEdit: Boolean = false,
    val isDeleteDialogOpen: Boolean = false,
    val transactionToDelete: String? = null,
    val isDeletingTransaction: Boolean = false,
    val isExportPdfDialogOpen: Boolean = false,
    val isExportExcelDialogOpen: Boolean = false,
    val isExportingPdf: Boolean = false,
    val isExportingExcel: Boolean = false,
    val snackbarMessage: SnackbarMessage? = null
)

