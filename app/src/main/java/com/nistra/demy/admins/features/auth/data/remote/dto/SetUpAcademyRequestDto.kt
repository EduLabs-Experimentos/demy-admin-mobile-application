package com.nistra.demy.admins.features.auth.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SetUpAcademyRequestDto(
    val academyName: String,
    val academyDescription: String,
    val street: String,
    val district: String,
    val province: String,
    val department: String,
    val emailAddress: String,
    val countryCode: String,
    val phone: String,
    val ruc: String,
    val administratorId: Long
)
