package com.nistra.demy.admins.features.auth.data.remote.dto

data class SignUpRequestDto(
    val emailAddress: String,
    val password: String,
    val roles: List<String>
)
