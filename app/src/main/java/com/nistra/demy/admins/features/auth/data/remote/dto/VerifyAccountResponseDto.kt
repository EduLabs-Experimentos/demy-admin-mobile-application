package com.nistra.demy.admins.features.auth.data.remote.dto

data class VerifyAccountResponseDto(
    val id: String,
    val emailAddress: String,
    val token: String
)
