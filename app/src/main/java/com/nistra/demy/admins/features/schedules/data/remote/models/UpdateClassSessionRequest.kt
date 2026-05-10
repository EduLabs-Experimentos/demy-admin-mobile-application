package com.nistra.demy.admins.features.schedules.data.remote.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateClassSessionRequest(
    val classroomId: Long,
    val startTime: String,
    val endTime: String,
    val dayOfWeek: String
)
