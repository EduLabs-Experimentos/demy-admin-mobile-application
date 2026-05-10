package com.nistra.demy.admins.features.schedules.presentation.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.models.ClassSession
import com.nistra.demy.admins.features.schedules.presentation.state.ScheduleUiState
import com.nistra.demy.admins.features.schedules.presentation.model.ClassSessionFormData
import com.nistra.demy.admins.features.teachers.domain.model.Teacher
import com.nistra.demy.admins.features.courses.domain.models.Course
import java.util.Locale
import androidx.compose.ui.res.stringResource
import com.nistra.demy.admins.R

@Composable
fun RegisterSchedule(
    modifier: Modifier = Modifier,
    uiState: ScheduleUiState,
    onScheduleNameChange: (String) -> Unit,
    onSaveScheduleName: () -> Unit,
    onClearForm: () -> Unit,
    onSessionFormChange: (ClassSessionFormData) -> Unit,
    onAddClassSession: () -> Unit,
    onDeleteClassSession: (ClassSession) -> Unit
) {
    val scheduleToEdit = uiState.scheduleToEdit
    val isEditing = scheduleToEdit != null
    val scheduleName = uiState.nameForm.name
    val isScheduleFormLoading = uiState.isLoading
    val sessionFormLoading = uiState.isSessionFormLoading

    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
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
                    text = if (!isEditing) stringResource(R.string.schedules_register_title) else stringResource(R.string.schedules_edit_title_prefix) + scheduleName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            OutlinedTextField(
                value = scheduleName,
                onValueChange = onScheduleNameChange,
                label = { Text(stringResource(R.string.schedules_name_label)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                enabled = !isScheduleFormLoading
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onSaveScheduleName,
                    modifier = Modifier.weight(1f).height(48.dp),
                    enabled = uiState.nameForm.isFormValid && !isScheduleFormLoading
                ) {
                    if (isScheduleFormLoading) {
                        CircularProgressIndicator(Modifier.size(20.dp))
                    } else {
                        Text(text = if (!isEditing) stringResource(R.string.schedules_create_button) else stringResource(R.string.schedules_update_name_button))
                    }
                }

                if (isEditing || scheduleName.isNotBlank()) {
                    OutlinedButton(
                        onClick = onClearForm,
                        modifier = Modifier.weight(1f).height(48.dp),
                        enabled = !isScheduleFormLoading
                    ) {
                        Icon(Icons.Default.Clear, contentDescription = stringResource(R.string.action_cancel))
                        Spacer(Modifier.width(4.dp))
                        Text(stringResource(R.string.action_cancel))
                    }
                }
            }

            uiState.error?.let { errorMsg ->
                Text(text = errorMsg, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }

            if (isEditing && scheduleToEdit != null) {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ScheduleSessionsContent(
                    schedule = scheduleToEdit,
                    formData = uiState.sessionForm,
                    availableCourses = uiState.availableCourses,
                    availableClassrooms = uiState.availableClassrooms,
                    availableTeachers = uiState.availableTeachers,
                    onSessionFormChange = onSessionFormChange,
                    onAddClassSession = onAddClassSession,
                    onDeleteClassSession = onDeleteClassSession,
                    isLoading = sessionFormLoading
                )
            }
        }
    }
}

@Composable
private fun ScheduleSessionsContent(
    schedule: Schedule,
    formData: ClassSessionFormData,
    availableCourses: List<Course>,
    availableClassrooms: List<Classroom>,
    availableTeachers: List<Teacher>,
    onSessionFormChange: (ClassSessionFormData) -> Unit,
    onAddClassSession: () -> Unit,
    onDeleteClassSession: (ClassSession) -> Unit,
    isLoading: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            stringResource(R.string.schedules_sessions_count_title, schedule.classSessions.size),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )

        if (schedule.classSessions.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                schedule.classSessions.forEach { session ->
                    ClassSessionListItem(session = session, onDelete = { onDeleteClassSession(session) })
                }
            }
        } else {
            Text(stringResource(R.string.schedules_no_sessions), style = MaterialTheme.typography.bodyMedium)
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        AddClassSessionForm(
            formData = formData,
            availableCourses = availableCourses,
            availableClassrooms = availableClassrooms,
            availableTeachers = availableTeachers,
            onFormChange = onSessionFormChange,
            onAddSession = onAddClassSession,
            isLoading = isLoading
        )
    }
}


@Composable
fun ClassSessionListItem(
    session: ClassSession,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${session.course.code} (${session.course.name})",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${session.dayOfWeek.name.lowercase().capitalize(Locale.getDefault())} ${session.timeRange.startTime} - ${session.timeRange.endTime}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = stringResource(R.string.schedules_session_classroom_prefix, session.classroom.code),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = stringResource(R.string.schedules_session_teacher_prefix, session.teacher.firstName + " " + session.teacher.lastName),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = stringResource(R.string.schedules_session_delete_cd),
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
