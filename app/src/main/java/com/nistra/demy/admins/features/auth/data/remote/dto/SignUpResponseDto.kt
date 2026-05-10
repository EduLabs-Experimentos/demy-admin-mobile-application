package com.nistra.demy.admins.features.auth.data.remote.dto

data class SignUpResponseDto(
    val id: String,
    val emailAddress: String,
    val roles: List<String>
)
