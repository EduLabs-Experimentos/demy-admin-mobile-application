package com.nistra.demy.admins.features.auth.data.remote.dto

data class VerifyAccountRequestDto(
    val email: String,
    val code: String
)
