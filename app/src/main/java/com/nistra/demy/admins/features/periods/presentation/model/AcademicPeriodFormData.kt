package com.nistra.demy.admins.features.periods.presentation.model

import java.time.LocalDate

data class AcademicPeriodFormData(
    val name: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val isActive: Boolean = true
)
