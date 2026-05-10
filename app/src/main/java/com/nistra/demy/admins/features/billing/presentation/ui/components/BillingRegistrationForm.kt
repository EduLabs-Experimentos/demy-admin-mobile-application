package com.nistra.demy.admins.features.billing.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.forms.FormCard
import com.nistra.demy.admins.features.billing.presentation.model.BillingAccountFormData

@Composable
fun BillingRegistrationForm(
    formData: BillingAccountFormData,
    onFormChange: (BillingAccountFormData) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
){
    FormCard(
        title = stringResource(R.string.billing_register_title),
        description = stringResource(R.string.billing_register_description),
        modifier = modifier
    ) {
        // Name fields
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = formData.studentId,
                onValueChange = { onFormChange(formData.copy(studentId = it)) },
                label = { Text(stringResource(R.string.billing_student_id)) },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                },
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
                    stringResource(R.string.billing_register_button_loading)
                } else {
                    stringResource(R.string.billing_register_button)
                }
            )
        }
    }
}
