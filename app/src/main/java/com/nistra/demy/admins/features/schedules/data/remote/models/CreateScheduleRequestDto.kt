package com.nistra.demy.admins.features.schedules.data.remote.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CreateScheduleRequestDto(
    val name: String
)
