package com.nistra.demy.admins.features.accounting.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.accounting.domain.model.Transaction
import com.nistra.demy.admins.features.accounting.presentation.utils.toCurrency
import com.nistra.demy.admins.features.accounting.presentation.utils.toLocalizedString
import com.nistra.demy.admins.features.accounting.presentation.utils.toPaymentMethod
import com.nistra.demy.admins.features.accounting.presentation.utils.toTransactionCategory
import com.nistra.demy.admins.features.accounting.presentation.utils.toTransactionType
import com.nistra.demy.admins.features.finance.domain.model.Currency
import com.nistra.demy.admins.features.finance.domain.model.PaymentMethod
import com.nistra.demy.admins.features.finance.domain.model.TransactionCategory
import com.nistra.demy.admins.features.finance.domain.model.TransactionType

/**
 * Dialog component for editing a transaction.
 *
 * Displays a modal dialog with editable fields for all transaction properties.
 * Uses Material 3 components and supports i18n.
 *
 * @param transaction The transaction to edit.
 * @param onDismiss Callback when the dialog should be dismissed.
 * @param onSave Callback when the save button is clicked with the updated transaction.
 * @param isSaving Whether a save operation is in progress.
 * @author Salim Ramirez
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTransactionDialog(
    transaction: Transaction,
    onDismiss: () -> Unit,
    onSave: (Transaction) -> Unit,
    isSaving: Boolean = false
) {
    var editedType by remember { mutableStateOf(transaction.type.toTransactionType()) }
    var editedCategory by remember { mutableStateOf(transaction.category.toTransactionCategory()) }
    var editedMethod by remember { mutableStateOf(transaction.method.toPaymentMethod()) }
    var editedAmount by remember { mutableStateOf(transaction.amount) }
    var editedCurrency by remember { mutableStateOf(transaction.currency.toCurrency() ?: Currency.PEN) }
    var editedDescription by remember { mutableStateOf(transaction.description ?: "") }
    var editedDate by remember { mutableStateOf(transaction.date) }

    var typeExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }
    var methodExpanded by remember { mutableStateOf(false) }
    var currencyExpanded by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = { if (!isSaving) onDismiss() },
        title = {
            Column {
                Text(
                    text = stringResource(R.string.accounting_edit_dialog_title),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = stringResource(R.string.accounting_edit_dialog_description),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Type and Category fields
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Transaction Type Dropdown
                    ExposedDropdownMenuBox(
                        expanded = typeExpanded,
                        onExpandedChange = { typeExpanded = it },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = editedType?.toLocalizedString() ?: "",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text(stringResource(R.string.accounting_edit_field_type)) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = typeExpanded) },
                            leadingIcon = { Icon(Icons.Default.Payment, contentDescription = null) },
                            modifier = Modifier
                                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                                .fillMaxWidth(),
                            singleLine = true,
                            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                            enabled = !isSaving
                        )

                        ExposedDropdownMenu(
                            expanded = typeExpanded,
                            onDismissRequest = { typeExpanded = false }
                        ) {
                            TransactionType.entries.forEach { type ->
                                DropdownMenuItem(
                                    text = { Text(type.toLocalizedString()) },
                                    onClick = {
                                        editedType = type
                                        typeExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    // Transaction Category Dropdown
                    ExposedDropdownMenuBox(
                        expanded = categoryExpanded,
                        onExpandedChange = { categoryExpanded = it },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = editedCategory?.toLocalizedString() ?: "",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text(stringResource(R.string.accounting_edit_field_category)) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                            leadingIcon = { Icon(Icons.Default.Category, contentDescription = null) },
                            modifier = Modifier
                                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                                .fillMaxWidth(),
                            singleLine = true,
                            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                            enabled = !isSaving
                        )

                        ExposedDropdownMenu(
                            expanded = categoryExpanded,
                            onDismissRequest = { categoryExpanded = false }
                        ) {
                            TransactionCategory.entries.forEach { category ->
                                DropdownMenuItem(
                                    text = { Text(category.toLocalizedString()) },
                                    onClick = {
                                        editedCategory = category
                                        categoryExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                // Payment Method Dropdown
                ExposedDropdownMenuBox(
                    expanded = methodExpanded,
                    onExpandedChange = { methodExpanded = it },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = editedMethod?.toLocalizedString() ?: "",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.accounting_edit_field_method)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = methodExpanded) },
                        leadingIcon = { Icon(Icons.Default.Payment, contentDescription = null) },
                        modifier = Modifier
                            .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                            .fillMaxWidth(),
                        singleLine = true,
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                        enabled = !isSaving
                    )

                    ExposedDropdownMenu(
                        expanded = methodExpanded,
                        onDismissRequest = { methodExpanded = false }
                    ) {
                        PaymentMethod.entries.forEach { method ->
                            DropdownMenuItem(
                                text = { Text(method.toLocalizedString()) },
                                onClick = {
                                    editedMethod = method
                                    methodExpanded = false
                                }
                            )
                        }
                    }
                }

                // Amount and Currency fields
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = editedAmount,
                        onValueChange = { editedAmount = it },
                        label = { Text(stringResource(R.string.accounting_edit_field_amount)) },
                        leadingIcon = { Icon(Icons.Default.AttachMoney, contentDescription = null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        enabled = !isSaving
                    )

                    // Currency Dropdown
                    ExposedDropdownMenuBox(
                        expanded = currencyExpanded,
                        onExpandedChange = { currencyExpanded = it },
                        modifier = Modifier.width(120.dp)
                    ) {
                        OutlinedTextField(
                            value = editedCurrency.toLocalizedString(),
                            onValueChange = {},
                            readOnly = true,
                            label = { Text(stringResource(R.string.accounting_edit_field_currency)) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = currencyExpanded) },
                            modifier = Modifier
                                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                                .fillMaxWidth(),
                            singleLine = true,
                            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                            enabled = !isSaving
                        )

                        ExposedDropdownMenu(
                            expanded = currencyExpanded,
                            onDismissRequest = { currencyExpanded = false }
                        ) {
                            Currency.entries.forEach { currency ->
                                DropdownMenuItem(
                                    text = { Text(currency.toLocalizedString()) },
                                    onClick = {
                                        editedCurrency = currency
                                        currencyExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                // Date field with DatePicker
                OutlinedTextField(
                    value = editedDate,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(stringResource(R.string.accounting_edit_field_date)) },
                    leadingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null) },
                    trailingIcon = {
                        IconButton(onClick = { if (!isSaving) showDatePicker = true }) {
                            Icon(Icons.Default.CalendarToday, contentDescription = "Select date")
                        }
                    },
                    placeholder = { Text("yyyy-MM-dd") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    enabled = !isSaving
                )

                // Description field
                OutlinedTextField(
                    value = editedDescription,
                    onValueChange = { editedDescription = it },
                    label = { Text(stringResource(R.string.accounting_edit_field_description)) },
                    leadingIcon = { Icon(Icons.Default.Description, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3,
                    enabled = !isSaving
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val updatedTransaction = transaction.copy(
                        type = editedType?.value ?: transaction.type,
                        category = editedCategory?.value ?: transaction.category,
                        method = editedMethod?.value ?: transaction.method,
                        amount = editedAmount,
                        currency = editedCurrency.code,
                        description = editedDescription.ifBlank { null },
                        date = editedDate
                    )
                    onSave(updatedTransaction)
                },
                enabled = !isSaving && editedType != null && editedCategory != null && editedMethod != null
            ) {
                if (isSaving) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .width(16.dp)
                            .padding(2.dp),
                        strokeWidth = 2.dp
                    )
                }
                Text(stringResource(R.string.accounting_edit_action_save))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                enabled = !isSaving
            ) {
                Text(stringResource(R.string.accounting_edit_action_cancel))
            }
        }
    )

    // DatePicker Dialog
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                            dateFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")
                            editedDate = dateFormat.format(java.util.Date(millis))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text(stringResource(R.string.action_ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(stringResource(R.string.action_cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

