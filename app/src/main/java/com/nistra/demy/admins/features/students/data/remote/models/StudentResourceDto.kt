package com.nistra.demy.admins.features.students.data.remote.models

import java.time.LocalDate

data class StudentResourceDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val dni: String,
    val email: String,
    val sex: String,
    val birthDate: LocalDate,
    val street: String,
    val district: String,
    val province: String,
    val department: String,
    val countryCode: String,
    val phone: String
)
