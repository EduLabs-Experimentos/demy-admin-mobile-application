package com.nistra.demy.admins.features.billing.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nistra.demy.admins.core.designsystem.components.feedback.DemySnackbarHost
import com.nistra.demy.admins.core.designsystem.components.feedback.SnackbarEffect
import com.nistra.demy.admins.core.designsystem.components.feedback.rememberDemySnackbarState
import com.nistra.demy.admins.features.billing.presentation.ui.components.AddInvoiceDialog
import com.nistra.demy.admins.features.billing.presentation.viewmodel.BillingAccountDetailsViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.billing.presentation.ui.components.InvoiceListItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillingAccountDetailsScreen(
    viewModel: BillingAccountDetailsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val invoiceFormData by viewModel.invoiceFormData.collectAsState()

    val snackbarState = rememberDemySnackbarState()

    SnackbarEffect(
        message = uiState.snackbarMessage,
        snackbarState = snackbarState,
        onMessageShown = viewModel::clearSnackbarMessage
    )


    if (uiState.isAddInvoiceDialogVisible) {
        AddInvoiceDialog(
            formData = invoiceFormData,
            onFormChange = viewModel::onInvoiceFormFieldChange,
            onDismiss = { viewModel.onShowAddInvoiceDialog(false) },
            onConfirm = viewModel::onAddInvoiceConfirm
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.billing_details_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.billing_details_back_button_description)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                when {
                    uiState.isLoading -> {
                        CircularProgressIndicator()
                    }
                    uiState.error != null -> {
                        Text(
                            text = stringResource(R.string.billing_details_error_message, uiState.error!!),
                            color = MaterialTheme.colorScheme.error
                        )

                    }
                    uiState.billingAccount != null -> {
                        val account = uiState.billingAccount!!
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            Text(
                                text = stringResource(R.string.billing_dni_number_label, account.dniNumber),
                                style = MaterialTheme.typography.headlineSmall
                            )

                            Divider(modifier = Modifier.padding(vertical = 8.dp))


                            Text(
                                text = stringResource(R.string.billing_details_invoices_title, account.invoices.size),
                                style = MaterialTheme.typography.headlineSmall
                            )


                            if (account.invoices.isEmpty()) {
                                Text(stringResource(R.string.billing_details_no_invoices))
                            } else {
                                LazyColumn(
                                    modifier = Modifier.weight(1f),
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    contentPadding = PaddingValues(vertical = 8.dp)
                                ) {
                                    items(account.invoices) { invoice ->
                                        InvoiceListItemCard(
                                            invoice = invoice,
                                            onClick = { clickedInvoice ->
                                                if (clickedInvoice.status.uppercase() != "PAID") {
                                                    viewModel.markInvoiceAsPaid(clickedInvoice.id)
                                                }
                                            },
                                            onDelete = { clickedInvoice ->
                                                viewModel.deleteInvoice(clickedInvoice.id)
                                            }

                                        )
                                    }
                                }
                            }



                            Button(
                                onClick = { viewModel.onShowAddInvoiceDialog(true) },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(stringResource(R.string.billing_details_add_invoice_button))
                            }
                        }
                    }
                }
            }

            DemySnackbarHost(
                snackbarState = snackbarState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
