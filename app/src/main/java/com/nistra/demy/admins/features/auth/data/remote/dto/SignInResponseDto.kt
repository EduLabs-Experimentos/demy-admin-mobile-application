package com.nistra.demy.admins.features.auth.data.remote.dto

data class SignInResponseDto(
    val id: String,
    val emailAddress: String,
    val token: String
)
