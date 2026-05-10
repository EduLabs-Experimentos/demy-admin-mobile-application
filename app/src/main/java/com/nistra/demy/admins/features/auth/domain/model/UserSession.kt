package com.nistra.demy.admins.features.auth.domain.model

data class UserSession(
    val id: String,
    val email: String,
    val token: String?
)
