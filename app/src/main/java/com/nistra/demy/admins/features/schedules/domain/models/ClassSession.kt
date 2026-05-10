package com.nistra.demy.admins.features.schedules.domain.models

import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.teachers.domain.model.Teacher

data class ClassSession(
    val id: Long,
    val timeRange: TimeRange,
    val dayOfWeek: DayOfWeek,

    val course: Course,
    val teacher: Teacher,
    val classroom: Classroom,

    val durationMinutes: Int
)
