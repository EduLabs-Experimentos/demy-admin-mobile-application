package com.nistra.demy.admins.features.dashboard.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AcademyResourceDto(
    val academyName: String,
    val street: String,
    val district: String,
    val province: String,
    val department: String,
    val phoneNumber: String,
    val emailAddress: String,
    val ruc: String
)
