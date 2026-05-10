package com.nistra.demy.admins.features.auth.presentation.model

data class AcademySetupFormData(
    val academyName: String = "",
    val academyDescription: String = "",
    val street: String = "",
    val district: String = "",
    val province: String = "",
    val department: String = "",
    val emailAddress: String = "",
    val countryCode: String = "",
    val phone: String = "",
    val ruc: String = ""
)
