package com.nistra.demy.admins.features.classrooms.presentation.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.classrooms.presentation.model.ClassroomFormData
import androidx.compose.ui.res.stringResource
import com.nistra.demy.admins.R

@Composable
fun RegisterClassroom(
    modifier: Modifier = Modifier,
    classroomToEdit: Classroom?,
    formData: ClassroomFormData,
    isLoading: Boolean,
    errorMessage: String?,
    onFormChange: (ClassroomFormData) -> Unit,
    onSaveClassroomClick: () -> Unit,
    onClearFormClick: () -> Unit
) {
    val isEditing = classroomToEdit != null
    val isFormValid = formData.code.isNotBlank() && formData.campus.isNotBlank() && formData.capacity > 0

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
                    text = if (!isEditing) stringResource(R.string.classrooms_registration_title) else stringResource(R.string.classrooms_edit_title_prefix) + classroomToEdit!!.code,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            OutlinedTextField(
                value = formData.code,
                onValueChange = { onFormChange(formData.copy(code = it)) },
                label = { Text(stringResource(R.string.classrooms_code_label)) },
                leadingIcon = { Icon(Icons.Default.Key, contentDescription = stringResource(R.string.classrooms_code_cd)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = formData.capacityText,
                onValueChange = { onFormChange(formData.copy(capacityText = it.filter { char -> char.isDigit() })) },
                label = { Text(stringResource(R.string.classrooms_capacity_label)) },
                leadingIcon = { Icon(Icons.Default.Group, contentDescription = stringResource(R.string.classrooms_capacity_cd)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = formData.campus,
                onValueChange = { onFormChange(formData.copy(campus = it)) },
                label = { Text(stringResource(R.string.classrooms_campus_label)) },
                leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = stringResource(R.string.classrooms_campus_cd)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

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
                Button(
                    onClick = onSaveClassroomClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = isFormValid && !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp))
                    } else {
                        Text(
                            text = if (!isEditing) stringResource(R.string.classrooms_register_button) else stringResource(R.string.classrooms_action_save_changes),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                if (isEditing || formData.code.isNotBlank() || formData.capacityText.isNotBlank() || formData.campus.isNotBlank()) {
                    OutlinedButton(
                        onClick = onClearFormClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        enabled = !isLoading
                    ) {
                        Icon(Icons.Default.Clear, contentDescription = stringResource(R.string.classrooms_action_cancel))
                        Spacer(Modifier.width(4.dp))
                        Text(stringResource(R.string.classrooms_action_cancel))
                    }
                }
            }
        }
    }
}
