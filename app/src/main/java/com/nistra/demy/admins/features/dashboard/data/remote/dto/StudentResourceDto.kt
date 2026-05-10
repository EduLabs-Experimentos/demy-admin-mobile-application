package com.nistra.demy.admins.features.dashboard.data.remote.dto

import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
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
