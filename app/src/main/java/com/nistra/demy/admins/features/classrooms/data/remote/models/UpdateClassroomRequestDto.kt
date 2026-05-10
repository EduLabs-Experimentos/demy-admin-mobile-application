package com.nistra.demy.admins.features.classrooms.data.remote.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateClassroomRequestDto(
    val code: String,
    val capacity: Int,
    val campus: String
)
