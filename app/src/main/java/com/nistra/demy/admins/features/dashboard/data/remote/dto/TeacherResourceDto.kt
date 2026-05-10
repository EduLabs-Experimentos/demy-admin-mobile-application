package com.nistra.demy.admins.features.dashboard.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeacherResourceDto(
    val id: String,
    val firstName: String,
    val lastName: String,
    @field:Json(name = "emailAddress") val email: String,
    val countryCode: String,
    @field:Json(name = "phoneNumber") val phone: String,
    val academyId: String
)
