package com.nistra.demy.admins.features.invoicing.presentation.ui.components

import com.nistra.demy.admins.R
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nistra.demy.admins.core.designsystem.components.cards.ListingCard
import com.nistra.demy.admins.features.billing.presentation.ui.components.InvoiceListItemCard
import com.nistra.demy.admins.features.invoicing.domain.model.Invoice

@Composable
fun InvoicesListPanel(
    invoices: List<Invoice>,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onMarkAsPaid: (invoiceId: String, billingAccountId: String) -> Unit,
    onDeleteInvoice: (invoiceId: String, billingAccountId: String) -> Unit
) {
    ListingCard(
        title = stringResource(R.string.invoices_list_title),
        description = stringResource(R.string.invoices_list_description),
        isLoading = isLoading,
        emptyMessage = stringResource(R.string.invoices_list_empty_message),
        itemCount = invoices.size,
        itemCountLabel = stringResource(R.string.invoices_list_count_label, invoices.size),
        modifier = modifier
    ) {
        items(invoices) { invoice ->
            InvoiceListItemCard(
                invoice = invoice,
                onClick = { clickedInvoice ->
                    if (clickedInvoice.status.uppercase() != "PAID") {
                        onMarkAsPaid(clickedInvoice.id, clickedInvoice.billingAccountId)
                    }
                },
                onDelete = { clickedInvoice ->
                    onDeleteInvoice(clickedInvoice.id, clickedInvoice.billingAccountId)
                }
            )
        }
    }
}
