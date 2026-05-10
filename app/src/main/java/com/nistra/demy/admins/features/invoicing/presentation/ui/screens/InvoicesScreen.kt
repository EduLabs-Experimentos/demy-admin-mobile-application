package com.nistra.demy.admins.features.invoicing.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nistra.demy.admins.core.designsystem.components.feedback.DemySnackbarHost
import com.nistra.demy.admins.core.designsystem.components.feedback.SnackbarEffect
import com.nistra.demy.admins.core.designsystem.components.feedback.rememberDemySnackbarState
import com.nistra.demy.admins.features.billing.presentation.ui.components.BillingAccountsHeader
import com.nistra.demy.admins.features.invoicing.presentation.ui.components.InvoicesListPanel
import com.nistra.demy.admins.features.invoicing.presentation.viewmodel.InvoicesViewModel
import com.nistra.demy.admins.R

@Composable
fun InvoicesScreen(
    viewModel: InvoicesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val snackbarState = rememberDemySnackbarState()

    SnackbarEffect(
        message = uiState.snackbarMessage,
        snackbarState = snackbarState,
        onMessageShown = viewModel::clearSnackbarMessage
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            BillingAccountsHeader(
                title = stringResource(R.string.invoices_screen_title),
                description = stringResource(R.string.invoices_screen_description)
            )


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = viewModel::onSearchQueryChange,
                    label = { Text(stringResource(R.string.invoices_search_label)) },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = viewModel::searchInvoices) {
                            Icon(Icons.Default.Search,
                                contentDescription = stringResource(R.string.invoices_search_button_description))
                        }
                    },
                    singleLine = true
                )

                InvoicesListPanel(
                    invoices = uiState.invoices,
                    isLoading = uiState.isLoading,
                    onMarkAsPaid = viewModel::markAsPaid,
                    modifier = Modifier.weight(1f),
                    onDeleteInvoice = viewModel::deleteInvoice,
                )
            }
        }

        DemySnackbarHost(
            snackbarState = snackbarState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

