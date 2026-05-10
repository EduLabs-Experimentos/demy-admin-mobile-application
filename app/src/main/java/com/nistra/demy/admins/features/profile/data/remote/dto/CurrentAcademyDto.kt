package com.nistra.demy.admins.features.profile.data.remote.dto

data class CurrentAcademyDto(
    val academyName: String,
    val street: String?,
    val district: String?,
    val province: String?,
    val department: String?,
    val phoneNumber: String?,
    val emailAddress: String?,
    val ruc: String?
)
