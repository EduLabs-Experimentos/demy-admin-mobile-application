package com.nistra.demy.admins.features.teachers.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateTeacherRequestDto(
    val firstName: String,
    val lastName: String,
    @field:Json(name = "emailAddress") val email: String,
    val countryCode: String,
    val phone: String
)
