package com.nistra.demy.admins.features.schedules.presentation.state

import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.presentation.model.ClassSessionFormData
import com.nistra.demy.admins.features.schedules.presentation.model.ScheduleFormData
import com.nistra.demy.admins.features.teachers.domain.model.Teacher

data class ScheduleUiState(
    val schedules: List<Schedule> = emptyList(),
    val scheduleToEdit: Schedule? = null,
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,

    val availableCourses: List<Course> = emptyList(),
    val availableClassrooms: List<Classroom> = emptyList(),
    val availableTeachers: List<Teacher> = emptyList(),

    // Estado del formulario de Schedule Name
    val nameForm: ScheduleFormData = ScheduleFormData(),

    // Estado del formulario de Class Session
    val sessionForm: ClassSessionFormData = ClassSessionFormData(),
    val isSessionFormLoading: Boolean = false,
)
