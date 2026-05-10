package com.nistra.demy.admins.features.teachers.domain.model

data class Teacher(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val countryCode: String,
    val phone: String
)
