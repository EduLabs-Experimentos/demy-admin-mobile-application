package com.nistra.demy.admins.features.schedules.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.schedules.domain.models.DayOfWeek
import com.nistra.demy.admins.features.schedules.presentation.model.ClassSessionFormData
import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.teachers.domain.model.Teacher
import androidx.compose.ui.res.stringResource
import com.nistra.demy.admins.R
import android.annotation.SuppressLint

@Composable
fun AddClassSessionForm(
    formData: ClassSessionFormData,
    availableCourses: List<Course>,
    availableClassrooms: List<Classroom>,
    availableTeachers: List<Teacher>,
    onFormChange: (ClassSessionFormData) -> Unit,
    onAddSession: () -> Unit,
    isLoading: Boolean
) {
    Text(
        stringResource(R.string.schedules_session_add_title),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
    )
    Spacer(Modifier.height(8.dp))

    val teacherOptions = availableTeachers.associate { it.id to "${it.firstName} ${it.lastName}" }
    val courseOptions = availableCourses.associate { it.id to "${it.code} - ${it.name}" }
    val classroomOptions = availableClassrooms.associate { it.id to "C${it.code} - ${it.campus}" }
    val timeOptions = remember { generateTimeOptions() }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        DayOfWeekDropdown(
            label = stringResource(R.string.schedules_day_label),
            selectedDay = DayOfWeek.entries.find { it.name == formData.selectedDay },
            onDaySelected = { day -> onFormChange(formData.copy(selectedDay = day.name)) },
            leadingIcon = Icons.Filled.CalendarMonth
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {

            DropdownMenu(
                label = stringResource(R.string.schedules_time_start_label),
                options = timeOptions,
                selectedId = formData.startTime,
                onSelected = { time -> onFormChange(formData.copy(startTime = time)) },
                modifier = Modifier.weight(1f),
                leadingIcon = Icons.Filled.AccessTime
            )

            DropdownMenu(
                label = stringResource(R.string.schedules_time_end_label),
                options = timeOptions,
                selectedId = formData.endTime,
                onSelected = { time -> onFormChange(formData.copy(endTime = time)) },
                modifier = Modifier.weight(1f),
                leadingIcon = Icons.Filled.AccessTimeFilled
            )
        }

        DropdownMenu(
            label = stringResource(R.string.schedules_course_label),
            options = courseOptions,
            selectedId = formData.courseId,
            onSelected = { id -> onFormChange(formData.copy(courseId = id)) },
            leadingIcon = Icons.AutoMirrored.Filled.MenuBook
        )

        DropdownMenu(
            label = stringResource(R.string.schedules_classroom_label),
            options = classroomOptions,
            selectedId = formData.classroomId,
            onSelected = { id -> onFormChange(formData.copy(classroomId = id)) },
            leadingIcon = Icons.Filled.LocationOn
        )

        DropdownMenu(
            label = stringResource(R.string.schedules_teacher_label),
            options = teacherOptions,
            selectedId = formData.teacherId,
            onSelected = { id -> onFormChange(formData.copy(teacherId = id)) },
            leadingIcon = Icons.Filled.Person
        )

        Button(
            onClick = onAddSession,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            enabled = formData.isFormValid && !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(Modifier.size(20.dp), color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.schedules_session_add_cd))
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.schedules_session_add_button))
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
    leadingIcon: ImageVector? = null
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedName = options[selectedId] ?: label

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
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
            shape = RoundedCornerShape(8.dp)
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
fun DayOfWeekDropdown(
    label: String,
    selectedDay: DayOfWeek?,
    onDaySelected: (DayOfWeek) -> Unit,
    leadingIcon: ImageVector? = null
) {
    var expanded by remember { mutableStateOf(false) }

    fun DayOfWeek.getStringResId(): Int {
        return when (this) {
            DayOfWeek.MONDAY -> R.string.day_monday
            DayOfWeek.TUESDAY -> R.string.day_tuesday
            DayOfWeek.WEDNESDAY -> R.string.day_wednesday
            DayOfWeek.THURSDAY -> R.string.day_thursday
            DayOfWeek.FRIDAY -> R.string.day_friday
            DayOfWeek.SATURDAY -> R.string.day_saturday
            DayOfWeek.SUNDAY -> R.string.day_sunday
        }
    }

    val selectedText = selectedDay?.let { stringResource(it.getStringResId()) } ?: label

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedText,
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
            shape = RoundedCornerShape(8.dp)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DayOfWeek.entries.forEach { day ->
                DropdownMenuItem(
                    text = { Text(stringResource(day.getStringResId())) },
                    onClick = {
                        onDaySelected(day)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@SuppressLint("DefaultLocale")
private fun generateTimeOptions(): Map<String, String> {
    val options = mutableMapOf<String, String>()
    for (hour in 7..19) {
        for (minute in listOf(0, 30)) {
            val time = String.format("%02d:%02d", hour, minute)
            options[time] = time
        }
    }
    return options
}
