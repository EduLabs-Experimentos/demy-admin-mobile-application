package com.nistra.demy.admins.features.periods.domain.repository

import com.nistra.demy.admins.features.periods.domain.model.AcademicPeriod
import java.time.LocalDate

interface AcademicPeriodRepository {
    // Queries
    suspend fun getAllPeriods(): List<AcademicPeriod>

    suspend fun getPeriodById(id: Long): AcademicPeriod?

    // Commands
    suspend fun createPeriod(create: AcademicPeriod): AcademicPeriod

    suspend fun updatePeriod(update: AcademicPeriod): AcademicPeriod

    suspend fun deletePeriod(id: Long)
}
