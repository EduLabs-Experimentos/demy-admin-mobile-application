package com.nistra.demy.admins.features.auth.data.remote.dto

data class SignInRequestDto(
    val emailAddress: String,
    val password: String
)
