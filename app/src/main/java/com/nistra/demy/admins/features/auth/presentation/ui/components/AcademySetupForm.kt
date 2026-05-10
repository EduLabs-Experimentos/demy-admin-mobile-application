package com.nistra.demy.admins.features.auth.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.auth.presentation.model.AcademySetupFormData

@Composable
fun AcademySetupForm(
    formData: AcademySetupFormData,
    isLoading: Boolean,
    onFormChange: (AcademySetupFormData) -> Unit,
    onSetUpClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 8.dp)
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = formData.academyName,
            onValueChange = { onFormChange(formData.copy(academyName = it)) },
            label = { Text(stringResource(R.string.set_up_academy_academy_name)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = formData.academyDescription,
            onValueChange = { onFormChange(formData.copy(academyDescription = it)) },
            label = { Text(stringResource(R.string.set_up_academy_academy_description)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = formData.street,
            onValueChange = { onFormChange(formData.copy(street = it)) },
            label = { Text(stringResource(R.string.set_up_academy_street)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = formData.district,
            onValueChange = { onFormChange(formData.copy(district = it)) },
            label = { Text(stringResource(R.string.set_up_academy_district)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = formData.province,
            onValueChange = { onFormChange(formData.copy(province = it)) },
            label = { Text(stringResource(R.string.set_up_academy_province)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = formData.department,
            onValueChange = { onFormChange(formData.copy(department = it)) },
            label = { Text(stringResource(R.string.set_up_academy_department)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = formData.emailAddress,
            onValueChange = { onFormChange(formData.copy(emailAddress = it)) },
            label = { Text(stringResource(R.string.set_up_academy_email)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = formData.countryCode,
            onValueChange = { onFormChange(formData.copy(countryCode = it)) },
            label = { Text(stringResource(R.string.set_up_academy_country_code)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = formData.phone,
            onValueChange = { onFormChange(formData.copy(phone = it)) },
            label = { Text(stringResource(R.string.set_up_academy_phone)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = formData.ruc,
            onValueChange = { onFormChange(formData.copy(ruc = it)) },
            label = { Text(stringResource(R.string.set_up_academy_ruc)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onSetUpClick() },
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            } else {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = stringResource(R.string.set_up_academy_icon)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.set_up_academy_button))
            }
        }
    }
}
