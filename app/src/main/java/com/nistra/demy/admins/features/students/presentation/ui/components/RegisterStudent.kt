package com.nistra.demy.admins.features.students.presentation.ui.components

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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.students.domain.model.Student
import com.nistra.demy.admins.features.students.presentation.model.StudentFormData
import androidx.compose.runtime.*
import com.nistra.demy.admins.features.students.presentation.model.Sex

@Composable
fun RegisterStudent(
    modifier: Modifier = Modifier,
    studentToEdit: Student?,
    formData: StudentFormData,
    isLoading: Boolean,
    errorMessage: String?,
    onFormChange: (StudentFormData) -> Unit,
    onSaveStudentClick: () -> Unit,
    onClearFormClick: () -> Unit
) {
    val isEditing = studentToEdit != null

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
                    text =
                        if (!isEditing) stringResource(R.string.students_register_title)
                        else stringResource(R.string.students_edit_title, studentToEdit.firstName),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = formData.firstName,
                    onValueChange = { onFormChange(formData.copy(firstName = it)) },
                    label = { Text(stringResource(R.string.students_name_label)) },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = stringResource(R.string.students_name_label)) },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )

                OutlinedTextField(
                    value = formData.lastName,
                    onValueChange = { onFormChange(formData.copy(lastName = it)) },
                    label = { Text(stringResource(R.string.students_lastname_label)) },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = stringResource(R.string.students_lastname_label)) },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }

            OutlinedTextField(
                value = formData.dni,
                onValueChange = { onFormChange(formData.copy(dni = it)) },
                label = { Text(stringResource(R.string.students_dni_input)) },
                leadingIcon = { Icon(Icons.Default.PermIdentity, contentDescription = stringResource(R.string.students_dni_input)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            if (!isEditing) {
                OutlinedTextField(
                    value = formData.emailAddress,
                    onValueChange = { onFormChange(formData.copy(emailAddress = it)) },
                    label = { Text(stringResource(R.string.students_email_input)) },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = stringResource(R.string.students_email_input)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            } else {
                OutlinedTextField(
                    value = formData.emailAddress,
                    onValueChange = {},
                    enabled = false,
                    label = { Text(stringResource(R.string.students_email_input)) },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = stringResource(R.string.students_email_input)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }

            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                ) {
                OutlinedTextField(
                    value = formData.countryCode,
                    onValueChange = { onFormChange(formData.copy(countryCode = it)) },
                    label = { Text(stringResource(R.string.students_country_code)) },
                    modifier = Modifier.width(100.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = formData.phone,
                    onValueChange = { onFormChange(formData.copy(phone = it)) },
                    label = { Text(stringResource(R.string.students_phone_input)) },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = stringResource(R.string.students_phone_input)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }

            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                SexDropdown(
                    label = stringResource(R.string.students_sex_label),
                    selectedSex = Sex.entries.find { it.name == formData.sex },
                    onSexSelected = { selectedSex ->
                        onFormChange(formData.copy(sex = selectedSex.name))
                    },
                )

                OutlinedTextField(
                    value = formData.birthDate,
                    onValueChange = { onFormChange(formData.copy(birthDate = it)) },
                    label = { Text(stringResource(R.string.students_birthdate_label)) },
                    leadingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = stringResource(R.string.students_birthdate_label)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    shape = RoundedCornerShape(8.dp)
                )

            }

            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                OutlinedTextField(
                    value = formData.street,
                    onValueChange = { onFormChange(formData.copy(street = it)) },
                    label = { Text(stringResource(R.string.students_street_label)) },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )

                OutlinedTextField(
                    value = formData.district,
                    onValueChange = { onFormChange(formData.copy(district = it)) },
                    label = { Text(stringResource(R.string.students_district_label)) },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )

            }

            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                OutlinedTextField(
                    value = formData.province,
                    onValueChange = { onFormChange(formData.copy(province = it)) },
                    label = { Text(stringResource(R.string.students_province_label)) },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )

                OutlinedTextField(
                    value = formData.department,
                    onValueChange = { onFormChange(formData.copy(department = it)) },
                    label = { Text(stringResource(R.string.students_department_label)) },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }

            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button (
                    onClick = onSaveStudentClick,
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    enabled = !isLoading && formData.firstName.isNotBlank() && formData.lastName.isNotBlank()
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp))
                    } else {
                        Text(
                            text =
                                if (!isEditing) stringResource(R.string.students_register_button)
                                else stringResource(R.string.students_save_button),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                if (isEditing || formData.firstName.isNotBlank() || formData.lastName.isNotBlank()) {
                    OutlinedButton (
                        onClick = onClearFormClick,
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        enabled = !isLoading
                    ) {
                        Icon(Icons.Default.Clear,  contentDescription = stringResource(R.string.students_cancel_button))
                        Spacer(Modifier.width(4.dp))
                        Text(stringResource(R.string.students_cancel_button))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SexDropdown(
    label: String,
    selectedSex: Sex?,
    onSexSelected: (Sex) -> Unit,
    leadingIcon: ImageVector? = null
) {
    var expanded by remember { mutableStateOf(false) }


    fun Sex.getStringResId(): Int {
        return when (this) {
            Sex.MALE -> R.string.students_sex_male
            Sex.FEMALE -> R.string.students_sex_female
        }
    }

    val selectedText = selectedSex?.let { stringResource(it.getStringResId()) } ?: label

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.width(100.dp)
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
            modifier = Modifier.menuAnchor().fillMaxWidth().height(64.dp),
            shape = RoundedCornerShape(8.dp)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Sex.entries.forEach { sex ->
                DropdownMenuItem(
                    text = { Text(stringResource(sex.getStringResId())) },
                    onClick = {
                        onSexSelected(sex)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

