package com.nistra.demy.admins.features.schedules.data.remote.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AddClassSessionRequestDto(
    val startTime: String,
    val endTime: String,
    val dayOfWeek: String,
    val courseId: Long,
    val classroomId: Long,
    val teacherFirstName: String,
    val teacherLastName: String
)
