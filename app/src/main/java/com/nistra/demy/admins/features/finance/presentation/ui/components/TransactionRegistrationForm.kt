package com.nistra.demy.admins.features.finance.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.nistra.demy.admins.core.designsystem.components.forms.FormCard
import com.nistra.demy.admins.features.finance.domain.model.Currency
import com.nistra.demy.admins.features.finance.domain.model.PaymentMethod
import com.nistra.demy.admins.features.finance.domain.model.TransactionCategory
import com.nistra.demy.admins.features.finance.domain.model.TransactionType
import com.nistra.demy.admins.features.finance.presentation.model.TransactionFormData
import com.nistra.demy.admins.features.finance.presentation.utils.toLocalizedString

/**
 * Form component for transaction registration.
 *
 * Provides input fields for transaction information including type, category,
 * method, amount, currency, description, and date.
 * Uses the generic FormCard component for consistent styling.
 *
 * @param formData The current state of the form data.
 * @param onFormChange Callback invoked when form data changes.
 * @param onSubmit Callback invoked when the form is submitted.
 * @param modifier Optional [Modifier] for the form.
 * @param isLoading Whether the form is in a loading state.
 * @author Salim Ramirez
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionRegistrationForm(
    formData: TransactionFormData,
    onFormChange: (TransactionFormData) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    var typeExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }
    var methodExpanded by remember { mutableStateOf(false) }
    var currencyExpanded by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    FormCard(
        title = stringResource(R.string.finance_register_title),
        description = stringResource(R.string.finance_register_description),
        modifier = modifier
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
                    value = formData.type?.toLocalizedString() ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(stringResource(R.string.finance_type)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = typeExpanded) },
                    leadingIcon = {
                        Icon(Icons.Default.Payment, contentDescription = null)
                    },
                    modifier = Modifier
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth(),
                    singleLine = true,
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                )

                ExposedDropdownMenu(
                    expanded = typeExpanded,
                    onDismissRequest = { typeExpanded = false }
                ) {
                    TransactionType.entries.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type.toLocalizedString()) },
                            onClick = {
                                onFormChange(formData.copy(type = type))
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
                    value = formData.category?.toLocalizedString() ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(stringResource(R.string.finance_category)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                    leadingIcon = {
                        Icon(Icons.Default.Category, contentDescription = null)
                    },
                    modifier = Modifier
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth(),
                    singleLine = true,
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                )

                ExposedDropdownMenu(
                    expanded = categoryExpanded,
                    onDismissRequest = { categoryExpanded = false }
                ) {
                    TransactionCategory.entries.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category.toLocalizedString()) },
                            onClick = {
                                onFormChange(formData.copy(category = category))
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
                value = formData.method?.toLocalizedString() ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.finance_method)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = methodExpanded) },
                leadingIcon = {
                    Icon(Icons.Default.Payment, contentDescription = null)
                },
                modifier = Modifier
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth(),
                singleLine = true,
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
            )

            ExposedDropdownMenu(
                expanded = methodExpanded,
                onDismissRequest = { methodExpanded = false }
            ) {
                PaymentMethod.entries.forEach { method ->
                    DropdownMenuItem(
                        text = { Text(method.toLocalizedString()) },
                        onClick = {
                            onFormChange(formData.copy(method = method))
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
                value = formData.amount,
                onValueChange = { onFormChange(formData.copy(amount = it)) },
                label = { Text(stringResource(R.string.finance_amount)) },
                leadingIcon = {
                    Icon(Icons.Default.AttachMoney, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.weight(1f),
                singleLine = true
            )

            // Currency Dropdown
            ExposedDropdownMenuBox(
                expanded = currencyExpanded,
                onExpandedChange = { currencyExpanded = it },
                modifier = Modifier.width(140.dp)
            ) {
                OutlinedTextField(
                    value = formData.currency.toLocalizedString(),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(stringResource(R.string.finance_currency)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = currencyExpanded) },
                    modifier = Modifier
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth(),
                    singleLine = true,
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                )

                ExposedDropdownMenu(
                    expanded = currencyExpanded,
                    onDismissRequest = { currencyExpanded = false }
                ) {
                    Currency.entries.forEach { currency ->
                        DropdownMenuItem(
                            text = { Text(currency.toLocalizedString()) },
                            onClick = {
                                onFormChange(formData.copy(currency = currency))
                                currencyExpanded = false
                            }
                        )
                    }
                }
            }
        }

        // Date field with DatePicker
        OutlinedTextField(
            value = formData.date,
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.finance_date)) },
            leadingIcon = {
                Icon(Icons.Default.CalendarToday, contentDescription = null)
            },
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(Icons.Default.CalendarToday, contentDescription = "Select date")
                }
            },
            placeholder = { Text("yyyy-MM-dd") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
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
                                // Use UTC timezone to avoid date shifting
                                val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                                dateFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")
                                val formattedDate = dateFormat.format(java.util.Date(millis))
                                onFormChange(formData.copy(date = formattedDate))
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

        // Description field
        OutlinedTextField(
            value = formData.description,
            onValueChange = { onFormChange(formData.copy(description = it)) },
            label = { Text(stringResource(R.string.finance_description)) },
            leadingIcon = {
                Icon(Icons.Default.Description, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3,
            minLines = 2
        )

        // Submit button
        Button(
            onClick = onSubmit,
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(
                text = if (isLoading) {
                    stringResource(R.string.finance_register_button_loading)
                } else {
                    stringResource(R.string.finance_register_button)
                }
            )
        }
    }
}

