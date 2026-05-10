package com.nistra.demy.admins.features.periods.data.repository

import com.nistra.demy.admins.features.periods.data.datasource.remote.AcademicPeriodRemoteDataSource
import com.nistra.demy.admins.features.periods.data.mapper.toCreateRequestDto
import com.nistra.demy.admins.features.periods.data.mapper.toDomain
import com.nistra.demy.admins.features.periods.data.mapper.toUpdateRequestDto
import com.nistra.demy.admins.features.periods.domain.model.AcademicPeriod
import com.nistra.demy.admins.features.periods.domain.repository.AcademicPeriodRepository
import javax.inject.Inject

class AcademicPeriodRepositoryImpl @Inject constructor(
    private val academicPeriodRemoteDataSource: AcademicPeriodRemoteDataSource
) : AcademicPeriodRepository {

    override suspend fun getAllPeriods(): List<AcademicPeriod> {
        val  academicPeriodResponse = academicPeriodRemoteDataSource.fetchAllPeriods()
        return academicPeriodResponse.map { it.toDomain() }
    }

    override suspend fun getPeriodById(id: Long): AcademicPeriod {
        return academicPeriodRemoteDataSource.getPeriodById(id).toDomain()
    }

    override suspend fun createPeriod(create: AcademicPeriod): AcademicPeriod {
        val createdAcademicPeriodResourceDto = academicPeriodRemoteDataSource.createAcademicPeriod(create.toCreateRequestDto())
        return createdAcademicPeriodResourceDto.toDomain()
    }

    override suspend fun updatePeriod(update: AcademicPeriod): AcademicPeriod {
        val updatedAcademicPeriodResourceDto = academicPeriodRemoteDataSource.updateAcademicPeriod(update.id, update.toUpdateRequestDto())
        return updatedAcademicPeriodResourceDto.toDomain()
    }

    override suspend fun deletePeriod(id: Long) {
        academicPeriodRemoteDataSource.deleteAcademicPeriod(id)
    }
}
