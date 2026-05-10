package com.nistra.demy.admins.features.periods.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.periods.domain.model.AcademicPeriod
import com.nistra.demy.admins.features.periods.presentation.model.AcademicPeriodFormData

@Composable
fun RegisterPeriod(
    modifier: Modifier = Modifier,
    academicPeriodToEdit: AcademicPeriod?,
    formData: AcademicPeriodFormData,
    isLoading: Boolean,
    errorMessage: String?,
    onFormChange: (AcademicPeriodFormData) -> Unit,
    onSavePeriodClick: () -> Unit,
    onClearFormClick: () -> Unit
) {
    val isEditing = academicPeriodToEdit != null

    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
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
                    text = if (!isEditing)
                        stringResource(R.string.periods_register_title)
                    else
                        (stringResource(R.string.period_edit) + " " + academicPeriodToEdit.periodName),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            OutlinedTextField(
                value = formData.name,
                onValueChange = { onFormChange(formData.copy(name = it)) },
                label = { Text(stringResource(R.string.periods_name)) },
                leadingIcon = {
                    Icon(
                        Icons.AutoMirrored.Filled.MenuBook,
                        contentDescription = stringResource(R.string.periods_name)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )
            OutlinedTextField(
                value = formData.startDate,
                onValueChange = { onFormChange(formData.copy(startDate = it)) },
                label = { Text(stringResource(R.string.periods_start_date)) },
                leadingIcon = {
                    Icon(
                        Icons.Default.CalendarMonth,
                        contentDescription = stringResource(R.string.periods_start_date)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = formData.endDate,
                onValueChange = { onFormChange(formData.copy(endDate = it)) },
                label = { Text(stringResource(R.string.periods_end_date)) },
                leadingIcon = {
                    Icon(
                        Icons.Default.CalendarMonth,
                        contentDescription = stringResource(R.string.periods_end_date)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(8.dp)
            )

            if (isEditing) {
                // --- Estado ---
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.periods_is_active),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Switch(
                        checked = formData.isActive,
                        onCheckedChange = { onFormChange(formData.copy(isActive = it)) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                            checkedTrackColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // --- Mensaje de error ---
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // --- Botones ---
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onSavePeriodClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = formData.name.isNotBlank() &&
                        formData.startDate.isNotBlank() &&
                        formData.endDate.isNotBlank() &&
                        !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp))
                    } else {
                        Text(
                            text = if (!isEditing) stringResource(R.string.periods_register_button) else stringResource(R.string.periods_save_changes_button),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                if (isEditing || formData.name.isNotBlank()) {
                    OutlinedButton(
                        onClick = onClearFormClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        enabled = !isLoading
                    ) {
                        Icon(Icons.Default.Clear, contentDescription = stringResource(R.string.periods_cancel_button))
                        Spacer(Modifier.width(4.dp))
                        Text(stringResource(R.string.periods_cancel_button))
                    }
                }
            }
        }
    }
}
