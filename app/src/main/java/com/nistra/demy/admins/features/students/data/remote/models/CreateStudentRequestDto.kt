package com.nistra.demy.admins.features.students.data.remote.models

import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class CreateStudentRequestDto(
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
