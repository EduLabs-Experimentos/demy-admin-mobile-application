package com.nistra.demy.admins.features.dashboard.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseResourceDto(
    val id: Long,
    val name: String,
    val code: String,
    val description: String
)
