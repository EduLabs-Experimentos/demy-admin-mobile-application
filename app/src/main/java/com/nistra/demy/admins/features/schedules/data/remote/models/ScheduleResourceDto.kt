package com.nistra.demy.admins.features.schedules.data.remote.models

import com.squareup.moshi.JsonClass


data class ScheduleResourceDto(
    val id: Long?,
    val name: String?,
    val classSessions: List<ClassSessionResourceDto>?
)
