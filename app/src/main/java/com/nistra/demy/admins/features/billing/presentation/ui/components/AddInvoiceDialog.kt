package com.nistra.demy.admins.features.billing.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.invoicing.presentation.model.InvoiceFormData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import com.nistra.demy.admins.R

/**
 * Composable responsible for rendering a form to add an invoice to a billing account.
 * OptIn is necessary for the Date Picker
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddInvoiceDialog(
    formData: InvoiceFormData,
    onFormChange: (InvoiceFormData) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    /**
     * The state wasn’t separated into a ViewModel because it’s purely UI state, not business/domain state.
     */


    var showDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000)
    )

    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)


    val invoiceTypeOptions = mapOf(
        "STUDENT_ENROLLMENT" to stringResource(R.string.invoice_type_student_enrollment),
        "STUDENT_MONTHLY_FEE" to stringResource(R.string.invoice_type_student_monthly_fee),
        "STUDENT_ONE_TIME_PAYMENT" to stringResource(R.string.invoice_type_student_one_time_payment),
        "OTHER" to stringResource(R.string.invoice_type_other)
    )

    var isDropdownExpanded by remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.invoice_dialog_title)) },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                OutlinedTextField(
                    value = formData.description,
                    onValueChange = { onFormChange(formData.copy(description = it)) },
                    label = { Text(stringResource(R.string.invoice_dialog_description_label)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = formData.amount,
                        onValueChange = { onFormChange(formData.copy(amount = it)) },
                        label = { Text(stringResource(R.string.invoice_dialog_amount_label)) },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    OutlinedTextField(
                        value = formData.currency,
                        onValueChange = { onFormChange(formData.copy(currency = it.uppercase())) },
                        label = { Text(stringResource(R.string.invoice_dialog_amount_label)) },
                        modifier = Modifier.weight(0.5f),
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters)
                    )
                }

                ExposedDropdownMenuBox(
                    expanded = isDropdownExpanded,
                    onExpandedChange = { isDropdownExpanded = !isDropdownExpanded },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = invoiceTypeOptions[formData.invoiceType] ?: "",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.invoice_dialog_type_label)) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false }
                    ) {
                        invoiceTypeOptions.forEach { (backendValue, userText) ->
                            DropdownMenuItem(
                                text = { Text(userText) },
                                onClick = {
                                    onFormChange(formData.copy(invoiceType = backendValue))
                                    isDropdownExpanded = false
                                }
                            )
                        }
                    }
                }


                OutlinedTextField(
                    value = formData.dueDate,
                    onValueChange = {  },
                    label = { Text(stringResource(R.string.invoice_dialog_due_date_label)) },
                    placeholder = { Text(stringResource(R.string.invoice_dialog_due_date_placeholder)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDatePicker = true },
                    readOnly = true,
                    enabled = false,
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    trailingIcon = {
                        Icon(Icons.Default.DateRange,
                            contentDescription = stringResource(R.string.invoice_dialog_date_picker_icon_description))
                    }
                )
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(stringResource(R.string.invoice_dialog_confirm_button)) }
            },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.invoice_dialog_cancel_button))
            }
        }
    )


    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDateMillis = datePickerState.selectedDateMillis
                        if (selectedDateMillis != null) {

                            dateFormatter.timeZone = TimeZone.getTimeZone("UTC")
                            val selectedDateString = dateFormatter.format(Date(selectedDateMillis))
                            onFormChange(formData.copy(dueDate = selectedDateString))
                        }
                        showDatePicker = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(stringResource(R.string.invoice_dialog_date_picker_cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
