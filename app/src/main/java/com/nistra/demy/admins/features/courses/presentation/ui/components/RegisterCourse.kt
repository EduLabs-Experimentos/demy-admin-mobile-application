package com.nistra.demy.admins.features.courses.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.courses.presentation.model.CourseFormData
import androidx.compose.ui.res.stringResource
import com.nistra.demy.admins.R

@Composable
fun RegisterCourse(
    modifier: Modifier = Modifier,
    courseToEdit: Course?,
    formData: CourseFormData,
    isLoading: Boolean,
    errorMessage: String?,
    onFormChange: (CourseFormData) -> Unit,
    onSaveCourseClick: () -> Unit,
    onClearFormClick: () -> Unit
) {
    val isEditing = courseToEdit != null

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
                    text = if (!isEditing) stringResource(R.string.courses_registration_title) else stringResource(R.string.courses_edit_title_prefix) + courseToEdit!!.code,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            OutlinedTextField(
                value = formData.name,
                onValueChange = { onFormChange(formData.copy(name = it)) },
                label = { Text(stringResource(R.string.courses_name_label)) },
                leadingIcon = { Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = "Nombre del Curso") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = formData.code,
                onValueChange = { onFormChange(formData.copy(code = it)) },
                label = { Text(stringResource(R.string.courses_code_label)) },
                leadingIcon = { Icon(Icons.Default.Tag, contentDescription = "Código de Curso") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = formData.description,
                onValueChange = { onFormChange(formData.copy(description = it)) },
                label = { Text(stringResource(R.string.courses_description_label)) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4,
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
                    onClick = onSaveCourseClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = formData.name.isNotBlank() && formData.code.isNotBlank() && formData.description.isNotBlank() && !isLoading,
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp))
                    } else {
                        Text(
                            text = if (!isEditing) stringResource(R.string.courses_register_button) else stringResource(R.string.courses_action_save_changes),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                if (isEditing || formData.name.isNotBlank() || formData.code.isNotBlank() || formData.description.isNotBlank()) {
                    OutlinedButton(
                        onClick = onClearFormClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        enabled = !isLoading
                    ) {
                        Icon(Icons.Default.Clear, contentDescription = "Cancelar")
                        Spacer(Modifier.width(4.dp))
                        Text(stringResource(R.string.courses_action_cancel))
                    }
                }
            }
        }
    }
}
