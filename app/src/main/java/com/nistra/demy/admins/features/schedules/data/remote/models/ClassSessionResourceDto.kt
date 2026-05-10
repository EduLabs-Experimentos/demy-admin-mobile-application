package com.nistra.demy.admins.features.schedules.data.remote.models

import com.nistra.demy.admins.features.classrooms.data.remote.models.ClassroomResourceDto
import com.nistra.demy.admins.features.courses.data.remote.models.CourseResourceDto
import com.nistra.demy.admins.features.teachers.data.remote.dto.TeacherResourceDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClassSessionResourceDto(
    val id: Long?,
    val startTime: String?,
    val endTime: String?,
    val dayOfWeek: String?,

    val course: CourseResourceDto?,
    val classroom: ClassroomResourceDto?,
    val teacher: TeacherResourceDto?,
)
