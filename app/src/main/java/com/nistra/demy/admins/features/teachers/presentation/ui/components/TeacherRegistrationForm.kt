package com.nistra.demy.admins.features.teachers.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.forms.FormCard
import com.nistra.demy.admins.features.teachers.presentation.model.TeacherFormData

/**
 * Form component for teacher registration.
 *
 * Provides input fields for teacher information including name, email, and phone number.
 * Uses the generic FormCard component for consistent styling.
 *
 * @param formData The current state of the form data.
 * @param onFormChange Callback invoked when form data changes.
 * @param onSubmit Callback invoked when the form is submitted.
 * @param modifier Optional [Modifier] for the form.
 * @param isLoading Whether the form is in a loading state.
 * @author Salim Ramirez
 */
@Composable
fun TeacherRegistrationForm(
    formData: TeacherFormData,
    onFormChange: (TeacherFormData) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    FormCard(
        title = stringResource(R.string.teachers_register_title),
        description = stringResource(R.string.teachers_register_description),
        modifier = modifier
    ) {
        // Name fields
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = formData.firstName,
                onValueChange = { onFormChange(formData.copy(firstName = it)) },
                label = { Text(stringResource(R.string.teachers_first_name)) },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                },
                modifier = Modifier.weight(1f),
                singleLine = true
            )

            OutlinedTextField(
                value = formData.lastName,
                onValueChange = { onFormChange(formData.copy(lastName = it)) },
                label = { Text(stringResource(R.string.teachers_last_name)) },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
        }

        // Email field
        OutlinedTextField(
            value = formData.email,
            onValueChange = { onFormChange(formData.copy(email = it)) },
            label = { Text(stringResource(R.string.teachers_email)) },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Phone fields
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = formData.countryCode,
                onValueChange = { onFormChange(formData.copy(countryCode = it)) },
                label = { Text(stringResource(R.string.teachers_country_code)) },
                modifier = Modifier.width(100.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = formData.phone,
                onValueChange = { onFormChange(formData.copy(phone = it)) },
                label = { Text(stringResource(R.string.teachers_phone)) },
                leadingIcon = {
                    Icon(Icons.Default.Phone, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.weight(1f),
                singleLine = true
            )
        }

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
                    stringResource(R.string.teachers_register_button_loading)
                } else {
                    stringResource(R.string.teachers_register_button)
                }
            )
        }
    }
}
