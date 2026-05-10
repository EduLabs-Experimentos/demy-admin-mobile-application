package com.nistra.demy.admins.features.schedules.presentation.state

import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.teachers.domain.model.Teacher

data class ScheduleViewerUiState(
    val schedule: Schedule? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSessionFormVisible: Boolean = false,
    val isSessionFormSuccess: Boolean = false,

    val availableCourses: List<Course> = emptyList(),
    val availableClassrooms: List<Classroom> = emptyList(),
    val availableTeachers: List<Teacher> = emptyList(),
)
