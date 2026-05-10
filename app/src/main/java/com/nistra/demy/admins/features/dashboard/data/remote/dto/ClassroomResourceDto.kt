package com.nistra.demy.admins.features.dashboard.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClassroomResourceDto(
    val id: Long,
    val code: String,
    val capacity: Int,
    val campus: String
)
