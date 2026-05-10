package com.nistra.demy.admins.features.dashboard.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.text.StatItem

/**
 * Row displaying academic statistics: teachers/students, enrollments/schedules, courses/classrooms.
 *
 * All three cards have consistent height using IntrinsicSize.
 *
 * @param totalTeachers Total number of teachers.
 * @param totalStudents Total number of students.
 * @param totalEnrollments Total number of active enrollments.
 * @param totalSchedules Total number of available schedules.
 * @param totalCourses Total number of courses.
 * @param totalClassrooms Total number of classrooms.
 * @param modifier Modifier to be applied to the row.
 * @author Salim Ramirez
 */
@Composable
fun DashboardAcademicRow(
    totalTeachers: Int,
    totalStudents: Int,
    totalEnrollments: Int,
    totalSchedules: Int,
    totalCourses: Int,
    totalClassrooms: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Teachers & Students Card
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StatItem(
                    label = stringResource(R.string.dashboard_total_teachers),
                    value = totalTeachers.toString(),
                    icon = Icons.Default.Person,
                    contentColor = MaterialTheme.colorScheme.primaryContainer
                )
                StatItem(
                    label = stringResource(R.string.dashboard_total_students),
                    value = totalStudents.toString(),
                    icon = Icons.Default.School,
                    contentColor = MaterialTheme.colorScheme.primaryContainer
                )
            }
        }

        // Enrollments & Schedules Card
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFDCC6)
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StatItem(
                    label = stringResource(R.string.dashboard_active_enrollments),
                    value = totalEnrollments.toString(),
                    icon = Icons.Default.PersonAdd,
                    contentColor = MaterialTheme.colorScheme.secondaryContainer
                )
                StatItem(
                    label = stringResource(R.string.dashboard_available_schedules),
                    value = totalSchedules.toString(),
                    icon = Icons.Default.Schedule,
                    contentColor = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }

        // Courses & Classrooms Card
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StatItem(
                    label = stringResource(R.string.dashboard_total_courses),
                    value = totalCourses.toString(),
                    icon = Icons.Default.Class,
                    contentColor = MaterialTheme.colorScheme.tertiaryContainer
                )
                StatItem(
                    label = stringResource(R.string.dashboard_total_classrooms),
                    value = totalClassrooms.toString(),
                    icon = Icons.Default.Event,
                    contentColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            }
        }
    }
}

