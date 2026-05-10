package com.nistra.demy.admins.features.enrollments.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.enrollments.domain.model.Enrollment
import com.nistra.demy.admins.features.enrollments.domain.model.EnrollmentStatus
import com.nistra.demy.admins.features.enrollments.domain.model.PaymentStatus
import com.nistra.demy.admins.features.enrollments.presentation.model.EnrollmentFormData
import com.nistra.demy.admins.features.periods.domain.model.AcademicPeriod
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.students.domain.model.Student


@Composable
fun RegisterEnrollment(
    modifier: Modifier = Modifier,
    enrollmentToEdit: Enrollment?,
    formData: EnrollmentFormData,
    availableStudents: List<Student>,
    availablePeriods: List<AcademicPeriod>,
    availableSchedules: List<Schedule>,
    isLoading: Boolean,
    errorMessage: String?,
    onFormChange: (EnrollmentFormData) -> Unit,
    onSaveEnrollmentClick: () -> Unit,
    onClearFormClick: () -> Unit
) {
    val isEditing = enrollmentToEdit != null

    Card (
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = if (!isEditing) stringResource(R.string.enrollments_registration_title)
                    else stringResource(R.string.enrollments_edit_title_prefix) + enrollmentToEdit.id,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            val studentOptions = availableStudents.associate { it.id to "${it.firstName} ${it.lastName}" }
            val periodsOptions = availablePeriods.associate { it.id to it.periodName }
            val schedulesOptions = availableSchedules.associate { it.id to it.name }

            DropdownMenu (
                label = stringResource(R.string.enrollments_student_label),
                options = studentOptions,
                selectedId = formData.studentId,
                onSelected = { id -> onFormChange(formData.copy(studentId = id)) },
                leadingIcon = Icons.Filled.Person,
                enabled = !isEditing
            )

            DropdownMenu(
                label = stringResource(R.string.enrollments_period_label),
                options = periodsOptions,
                selectedId = formData.periodId,
                onSelected = { id -> onFormChange(formData.copy(periodId = id)) },
                leadingIcon = Icons.Filled.CalendarMonth,
                enabled = !isEditing
            )

            DropdownMenu(
                label = stringResource(R.string.enrollments_schedule_label),
                options = schedulesOptions,
                selectedId = formData.scheduleId,
                onSelected = { id -> onFormChange(formData.copy(scheduleId = id)) },
                leadingIcon = Icons.Filled.AccessTime,
                enabled = !isEditing
            )


            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
            ) {

                OutlinedTextField(
                    value = formData.currency,
                    onValueChange = { onFormChange(formData.copy(currency = it)) },
                    label = { Text(stringResource(R.string.enrollments_currency_label)) },
                    leadingIcon = { Icon(Icons.Filled.CurrencyExchange, contentDescription = "Moneda") },
                    modifier = Modifier.width(100.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )

                OutlinedTextField(
                    value = formData.amount,
                    onValueChange = { onFormChange(formData.copy(amount = it)) },
                    label = { Text(stringResource(R.string.enrollments_amount_label)) },
                    leadingIcon = { Icon(Icons.Filled.AttachMoney, contentDescription = "Monto") },
                    keyboardOptions = KeyboardOptions.Default,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )
            }


            PaymentStatusDropdown(
                label = stringResource(R.string.enrollments_payment_status_label),
                selectedPaymentStatus = PaymentStatus.entries.find { it.name == formData.paymentStatus },
                onPaymentStatusSelected = { selectedPaymentStatus ->
                    onFormChange(formData.copy(paymentStatus = selectedPaymentStatus.name))
                },
                )

            if (isEditing) {
                EnrollmentStatusDropdown(
                    label = stringResource(R.string.enrollments_enrollment_status_label),
                    selectedEnrollmentStatus = EnrollmentStatus.entries.find { it.name == formData.enrollmentStatus },
                    onEnrollmentStatusSelected = { selectedEnrollmentStatus ->
                        onFormChange(formData.copy(enrollmentStatus = selectedEnrollmentStatus.name)) },
                )
            }


            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button (
                    onClick = onSaveEnrollmentClick,
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    enabled = formData.isFormValid && !isLoading
                ) {
                    if (isLoading) CircularProgressIndicator(Modifier.size(20.dp))
                    else Text(
                        text = if (!isEditing) stringResource(R.string.enrollments_register_button)
                        else stringResource(R.string.enrollments_action_save_changes)
                    )
                }

                if (isEditing || formData.hasData()) {
                    OutlinedButton (
                        onClick = onClearFormClick,
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        enabled = !isLoading
                    ) {
                        Icon(Icons.Filled.Clear, contentDescription = "Cancelar")
                        Spacer(Modifier.width(4.dp))
                        Text(stringResource(R.string.enrollments_action_cancel))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownMenu(
    label: String,
    options: Map<T, String>,
    selectedId: T?,
    onSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedName = options[selectedId] ?: label

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { if (enabled) expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedName,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            leadingIcon = if (leadingIcon != null) {
                { Icon(leadingIcon, contentDescription = label) }
            } else {
                null
            },
            modifier = Modifier.menuAnchor().fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            enabled = enabled
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { (id, name) ->
                DropdownMenuItem(
                    text = { Text(name) },
                    onClick = {
                        onSelected(id)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentStatusDropdown(
    label: String,
    selectedPaymentStatus: PaymentStatus?,
    onPaymentStatusSelected: (PaymentStatus) -> Unit,
    leadingIcon: ImageVector? = null
) {
    var expanded by remember { mutableStateOf(false) }


    fun PaymentStatus.getStringResId(): Int {
        return when (this) {
            PaymentStatus.FAILED -> R.string.enrollments_payment_status_failed
            PaymentStatus.PAID -> R.string.enrollments_payment_status_paid
            PaymentStatus.PENDING -> R.string.enrollments_payment_status_pending
            PaymentStatus.REFUNDED -> R.string.enrollments_payment_status_refunding
        }
    }

    val selectedText = selectedPaymentStatus?.let { stringResource(it.getStringResId()) } ?: label

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            leadingIcon = if (leadingIcon != null) {
                { Icon(leadingIcon, contentDescription = label) }
            } else null,
            modifier = Modifier.menuAnchor().fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            PaymentStatus.entries.forEach { paymentStatus ->
                DropdownMenuItem(
                    text = { Text(stringResource(paymentStatus.getStringResId())) },
                    onClick = {
                        onPaymentStatusSelected(paymentStatus)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnrollmentStatusDropdown(
    label: String,
    selectedEnrollmentStatus: EnrollmentStatus?,
    onEnrollmentStatusSelected: (EnrollmentStatus) -> Unit,
    leadingIcon: ImageVector? = null
) {
    var expanded by remember { mutableStateOf(false) }


    fun EnrollmentStatus.getStringResId(): Int {
        return when (this) {
            EnrollmentStatus.ACTIVE -> R.string.enrollment_status_active
            EnrollmentStatus.FINALIZED -> R.string.students_status_finalized
            EnrollmentStatus.WITHDRAWN -> R.string.enrollment_status_withdraw
        }
    }

    val selectedText = selectedEnrollmentStatus?.let { stringResource(it.getStringResId()) } ?: label

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            leadingIcon = if (leadingIcon != null) {
                { Icon(leadingIcon, contentDescription = label) }
            } else null,
            modifier = Modifier.menuAnchor().fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            EnrollmentStatus.entries.forEach { enrollmentStatus ->
                DropdownMenuItem(
                    text = { Text(stringResource(enrollmentStatus.getStringResId())) },
                    onClick = {
                        onEnrollmentStatusSelected(enrollmentStatus)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}




