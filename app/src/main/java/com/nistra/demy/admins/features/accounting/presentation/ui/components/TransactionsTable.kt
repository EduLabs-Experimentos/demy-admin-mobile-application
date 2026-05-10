package com.nistra.demy.admins.features.accounting.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.table.TableActionCell
import com.nistra.demy.admins.core.designsystem.components.table.TableCell
import com.nistra.demy.admins.core.designsystem.components.table.TableHeaderCell
import com.nistra.demy.admins.core.designsystem.components.table.TableHeaderRow
import com.nistra.demy.admins.core.designsystem.components.table.TableRow
import com.nistra.demy.admins.features.accounting.domain.model.Transaction
import com.nistra.demy.admins.features.accounting.presentation.utils.getPaymentMethodString
import com.nistra.demy.admins.features.accounting.presentation.utils.getTransactionCategoryString
import com.nistra.demy.admins.features.accounting.presentation.utils.getTransactionTypeString

/**
 * Table displaying all transactions with search filtering.
 *
 * @param transactions List of transactions to display.
 * @param isLoading Whether data is being loaded.
 * @param searchQuery Current search query for empty state message.
 * @param onEditClick Callback when edit button is clicked.
 * @param onDeleteClick Callback when delete button is clicked.
 * @param modifier Optional [Modifier] for the component.
 * @author Salim Ramirez
 */
@Composable
fun TransactionsTable(
    transactions: List<Transaction>,
    isLoading: Boolean,
    searchQuery: String,
    onEditClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Table Header
            TableHeaderRow {
                TableHeaderCell(
                    text = stringResource(R.string.accounting_table_number),
                    weight = 0.5f
                )
                TableHeaderCell(
                    text = stringResource(R.string.accounting_table_type),
                    weight = 0.8f
                )
                TableHeaderCell(
                    text = stringResource(R.string.accounting_table_category),
                    weight = 1.2f
                )
                TableHeaderCell(
                    text = stringResource(R.string.accounting_table_method),
                    weight = 1f
                )
                TableHeaderCell(
                    text = stringResource(R.string.accounting_table_amount),
                    weight = 0.8f
                )
                TableHeaderCell(
                    text = stringResource(R.string.accounting_table_date),
                    weight = 0.8f
                )
                TableHeaderCell(
                    text = stringResource(R.string.accounting_table_description),
                    weight = 1.5f
                )
                TableHeaderCell(
                    text = stringResource(R.string.accounting_table_actions),
                    weight = 0.8f
                )
            }

            // Table Content
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                transactions.isEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (searchQuery.isBlank()) {
                                stringResource(R.string.accounting_empty_state)
                            } else {
                                stringResource(R.string.accounting_empty_search)
                            },
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        itemsIndexed(transactions) { index, transaction ->
                            TransactionTableRow(
                                rowNumber = index + 1,
                                transaction = transaction,
                                onEditClick = { onEditClick(transaction.id) },
                                onDeleteClick = { onDeleteClick(transaction.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Single row in the transactions table.
 *
 * @param rowNumber The sequential number of this row in the table.
 * @param transaction The transaction data to display.
 * @param onEditClick Callback when edit button is clicked.
 * @param onDeleteClick Callback when delete button is clicked.
 * @author Salim Ramirez
 */
@Composable
private fun TransactionTableRow(
    rowNumber: Int,
    transaction: Transaction,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    TableRow {
        TableCell(
            text = rowNumber.toString(),
            weight = 0.5f
        )
        TableCell(
            text = getTransactionTypeString(transaction.type),
            weight = 0.8f
        )
        TableCell(
            text = getTransactionCategoryString(transaction.category),
            weight = 1.2f
        )
        TableCell(
            text = getPaymentMethodString(transaction.method),
            weight = 1f
        )
        TableCell(
            text = "${transaction.amount} ${transaction.currency}",
            weight = 0.8f
        )
        TableCell(
            text = transaction.date,
            weight = 0.8f
        )
        TableCell(
            text = transaction.description ?: "-",
            weight = 1.5f
        )
        TableActionCell(
            weight = 0.8f
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = stringResource(R.string.accounting_action_edit),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.accounting_action_delete),
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

