package com.nistra.demy.admins.features.classrooms.domain.models

data class Classroom(
    val id: Long,
    val code: String,
    val capacity: Int,
    val campus: String
)
