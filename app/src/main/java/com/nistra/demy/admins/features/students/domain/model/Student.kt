package com.nistra.demy.admins.features.students.domain.model

import java.time.LocalDate

data class Student(
    val id: Long = 0L,
    val firstName: String,
    val lastName: String,
    val dni: String,
    val emailAddress: String,
    val sex: String,
    val birthDate: LocalDate,
    val street: String,
    val district: String,
    val province: String,
    val department: String,
    val countryCode: String,
    val phone: String
)
