package com.nistra.demy.admins.features.auth.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompleteAccountResponseDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val dniNumber: String
)
