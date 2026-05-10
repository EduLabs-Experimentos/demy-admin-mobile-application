package com.nistra.demy.admins.features.auth.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompleteAccountRequestDto(
    val firstName: String,
    val lastName: String,
    val countryCode: String,
    @field:Json(name = "phone") val phoneNumber: String,
    val dniNumber: String,
    val userId: Long
)
