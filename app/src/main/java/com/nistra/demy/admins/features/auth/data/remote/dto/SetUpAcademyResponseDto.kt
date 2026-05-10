package com.nistra.demy.admins.features.auth.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SetUpAcademyResponseDto(
    val id: Long,
    val administratorId: Long,
    val academyName: String,
    val academyDescription: String,
    val street: String,
    val district: String,
    val province: String,
    val department: String,
    val emailAddress: String,
    val phoneNumber: String,
    val ruc: String
)
