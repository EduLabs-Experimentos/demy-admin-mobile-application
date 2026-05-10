package com.nistra.demy.admins.features.dashboard.presentation.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AcademicPeriodDetailsCard(
    totalStudents: Int,
    startDate: String,
    endDate: String,
    modifier: Modifier = Modifier
) {
    CardContainer(title = "Current academic period details", modifier = modifier) {
        LabeledValue(label = "Total students", value = totalStudents.toString(), valueSize = MaterialTheme.typography.headlineMedium)
        LabeledValue(label = "Start date", value = startDate, valueSize = MaterialTheme.typography.titleLarge)
        LabeledValue(label = "End date", value = endDate, valueSize = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun MostOverloadedTeacherCard(
    teacherName: String,
    modifier: Modifier = Modifier
) {
    CardContainer(title = "Most overloaded teacher", modifier = modifier) {
        Text(
            text = teacherName,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ClassroomsAndCoursesCard(
    totalCourses: Int,
    totalClassrooms: Int,
    modifier: Modifier = Modifier
) {
    CardContainer(title = "Classrooms and courses", modifier = modifier) {
        LabeledValue(label = "Total courses", value = totalCourses.toString(), valueSize = MaterialTheme.typography.headlineMedium)
        LabeledValue(label = "Total classrooms", value = totalClassrooms.toString(), valueSize = MaterialTheme.typography.headlineMedium)
    }
}
