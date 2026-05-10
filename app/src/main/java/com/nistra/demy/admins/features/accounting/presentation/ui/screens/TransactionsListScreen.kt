package com.nistra.demy.admins.features.accounting.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.feedback.DemySnackbarHost
import com.nistra.demy.admins.core.designsystem.components.feedback.SnackbarEffect
import com.nistra.demy.admins.core.designsystem.components.feedback.rememberDemySnackbarState
import com.nistra.demy.admins.features.accounting.presentation.ui.components.AccountingHeader
import com.nistra.demy.admins.features.accounting.presentation.ui.components.DeleteConfirmationDialog
import com.nistra.demy.admins.features.accounting.presentation.ui.components.EditTransactionDialog
import com.nistra.demy.admins.features.accounting.presentation.ui.components.ExportConfirmationDialog
import com.nistra.demy.admins.features.accounting.presentation.ui.components.ExportSection
import com.nistra.demy.admins.features.accounting.presentation.ui.components.ExportType
import com.nistra.demy.admins.features.accounting.presentation.ui.components.TransactionSearchSection
import com.nistra.demy.admins.features.accounting.presentation.ui.components.TransactionsTable
import com.nistra.demy.admins.features.accounting.presentation.viewmodel.AccountingViewModel

/**
 * Screen for viewing and managing accounting transactions.
 *
 * Displays a header with background image, search functionality, and transaction list table.
 *
 * @param viewModel The ViewModel managing the screen state and business logic.
 * @author Salim Ramirez
 */
@Composable
fun AccountingScreen(
    viewModel: AccountingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarState = rememberDemySnackbarState()

    SnackbarEffect(
        message = uiState.snackbarMessage,
        snackbarState = snackbarState,
        onMessageShown = viewModel::clearSnackbarMessage
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AccountingHeader(
                title = stringResource(R.string.accounting_screen_title),
                description = stringResource(R.string.accounting_screen_description)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TransactionSearchSection(
                    searchQuery = uiState.searchQuery,
                    onSearchQueryChange = viewModel::onSearchQueryChange,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )

                ExportSection(
                    onExportPdf = viewModel::onRequestExportPdf,
                    onExportExcel = viewModel::onRequestExportExcel,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
            }

            TransactionsTable(
                transactions = uiState.filteredTransactions,
                isLoading = uiState.isLoading,
                searchQuery = uiState.searchQuery,
                onEditClick = viewModel::onEditTransaction,
                onDeleteClick = viewModel::onDeleteTransaction,
                modifier = Modifier.weight(1f)
            )
        }

        DemySnackbarHost(
            snackbarState = snackbarState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

    // Edit Transaction Dialog
    if (uiState.isEditDialogOpen && uiState.transactionBeingEdited != null) {
        EditTransactionDialog(
            transaction = uiState.transactionBeingEdited!!,
            onDismiss = viewModel::onCloseEditDialog,
            onSave = viewModel::onSaveTransaction,
            isSaving = uiState.isSavingEdit
        )
    }

    // Delete Confirmation Dialog
    if (uiState.isDeleteDialogOpen) {
        DeleteConfirmationDialog(
            onDismiss = viewModel::onCloseDeleteDialog,
            onConfirm = viewModel::onConfirmDelete,
            isDeleting = uiState.isDeletingTransaction
        )
    }

    // Export PDF Confirmation Dialog
    if (uiState.isExportPdfDialogOpen) {
        ExportConfirmationDialog(
            exportType = ExportType.PDF,
            isExporting = uiState.isExportingPdf,
            onDismiss = viewModel::onCloseExportPdfDialog,
            onConfirm = viewModel::onConfirmExportPdf
        )
    }

    // Export Excel Confirmation Dialog
    if (uiState.isExportExcelDialogOpen) {
        ExportConfirmationDialog(
            exportType = ExportType.EXCEL,
            isExporting = uiState.isExportingExcel,
            onDismiss = viewModel::onCloseExportExcelDialog,
            onConfirm = viewModel::onConfirmExportExcel
        )
    }
}

