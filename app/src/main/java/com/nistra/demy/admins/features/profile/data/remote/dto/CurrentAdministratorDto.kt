package com.nistra.demy.admins.features.profile.data.remote.dto

data class CurrentAdministratorDto(
    val firstName: String,
    val lastName: String,
    val emailAddress: String,
    val phoneNumber: String?,
    val dniNumber: String
)
