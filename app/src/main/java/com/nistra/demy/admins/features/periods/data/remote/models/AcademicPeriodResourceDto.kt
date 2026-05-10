package com.nistra.demy.admins.features.periods.data.remote.models

import java.time.LocalDate

data class AcademicPeriodResourceDto(
val id: Long,
val periodName: String,
val startDate: LocalDate,
val endDate: LocalDate,
val isActive: Boolean,
)

