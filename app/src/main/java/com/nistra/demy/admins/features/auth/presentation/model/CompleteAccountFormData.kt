package com.nistra.demy.admins.features.auth.presentation.model

data class CompleteAccountFormData(
    val firstName: String = "",
    val lastName: String = "",
    val countryCode: String = "",
    val phoneNumber: String = "",
    val dniNumber: String = ""
)

