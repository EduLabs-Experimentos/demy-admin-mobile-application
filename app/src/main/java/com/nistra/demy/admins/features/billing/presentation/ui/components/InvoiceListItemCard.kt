package com.nistra.demy.admins.features.billing.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.cards.ListItemCard
import com.nistra.demy.admins.core.designsystem.components.text.IconLabelRow
import com.nistra.demy.admins.features.invoicing.domain.model.Invoice

@Composable
fun InvoiceListItemCard(
    invoice: Invoice,
    modifier: Modifier = Modifier,
    onClick: (Invoice) -> Unit = {},
    onDelete: (Invoice) -> Unit = {}
) {
    ListItemCard(
        onClick = { onClick(invoice) },
        modifier = modifier,
        containerColor = when (invoice.status) {
            "PAID" -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
            "PENDING" -> MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
            "OVERDUE" -> MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f)
            else -> MaterialTheme.colorScheme.surfaceVariant
        },

        mainContent = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconLabelRow(
                    icon = Icons.Default.Description,
                    text = invoice.description,
                    textStyle = MaterialTheme.typography.titleMedium,
                    textColor = MaterialTheme.colorScheme.onSurface
                )

                val currencySymbol = when (invoice.currency.uppercase()) {
                    "PEN" -> "S/"
                    "USD" -> "$"
                    else -> invoice.currency
                }

                Text(
                    text = "$currencySymbol ${invoice.amount}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconLabelRow(
                    icon = Icons.Default.CalendarMonth,
                    text = "${stringResource(R.string.invoice_due_date_label)} ${invoice.dueDate}",
                    iconColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
                IconLabelRow(
                    icon = Icons.Default.Info,
                    text = invoice.status,
                    iconColor = when (invoice.status) {
                        "PAID" -> Color(0xFF388E3C)
                        "PENDING" -> Color(0xFFF57C00)
                        "OVERDUE" -> MaterialTheme.colorScheme.error
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
        }
    },
        actions = {
            IconButton(onClick = { onDelete(invoice) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete invoice",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }

    )
}
