package com.nistra.demy.admins.features.courses.data.remote.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateCourseRequestDto(
    val name: String,
    val code: String,
    val description: String
)
