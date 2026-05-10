package com.nistra.demy.admins.features.periods.data.remote.models

import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class CreateAcademicPeriodRequestDto(
    val periodName: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val isActive: Boolean,
)

