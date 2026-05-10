package com.nistra.demy.admins.features.periods.domain.model

import java.time.LocalDate

data class AcademicPeriod(
    val id: Long,
    val periodName: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val isActive: Boolean
)
