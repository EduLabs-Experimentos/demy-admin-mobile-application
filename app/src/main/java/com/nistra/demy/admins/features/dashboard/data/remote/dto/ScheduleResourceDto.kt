package com.nistra.demy.admins.features.dashboard.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScheduleResourceDto(
    val id: Long?,
    val name: String?,
    val classSessions: List<ClassSessionResourceDto>?
)
