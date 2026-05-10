package com.nistra.demy.admins.features.periods.data.datasource.remote

import com.nistra.demy.admins.features.periods.data.remote.models.AcademicPeriodResourceDto
import com.nistra.demy.admins.features.periods.data.remote.models.CreateAcademicPeriodRequestDto
import com.nistra.demy.admins.features.periods.data.remote.models.UpdateAcademicPeriodRequestDto

interface AcademicPeriodRemoteDataSource {
    suspend fun fetchAllPeriods(): List<AcademicPeriodResourceDto>
    suspend fun getPeriodById(id: Long): AcademicPeriodResourceDto
    suspend fun createAcademicPeriod(request: CreateAcademicPeriodRequestDto): AcademicPeriodResourceDto

    suspend fun updateAcademicPeriod(id: Long, request: UpdateAcademicPeriodRequestDto): AcademicPeriodResourceDto

    suspend fun deleteAcademicPeriod(id: Long)
}
