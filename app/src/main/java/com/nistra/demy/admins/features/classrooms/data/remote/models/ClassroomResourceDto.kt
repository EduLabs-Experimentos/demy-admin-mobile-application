package com.nistra.demy.admins.features.classrooms.data.remote.models


data class ClassroomResourceDto(
    val id: Long,
    val code: String,
    val capacity: Int,
    val campus: String
)
