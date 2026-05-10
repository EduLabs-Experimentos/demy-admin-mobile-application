package com.nistra.demy.admins.features.students.presentation.model

data class StudentFormData(
    val firstName: String = "",
    val lastName: String = "",
    val dni: String = "",
    val emailAddress: String = "",
    val sex: String = "",
    val birthDate: String = "",
    val street: String = "",
    val district: String = "",
    val province: String = "",
    val department: String = "",
    val countryCode: String =  "+51",
    val phone: String = ""
)
