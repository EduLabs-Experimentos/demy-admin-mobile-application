package com.nistra.demy.admins.features.periods.data.datasource.remote

import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.periods.data.remote.api.AcademicPeriodApi
import com.nistra.demy.admins.features.periods.data.remote.models.AcademicPeriodResourceDto
import com.nistra.demy.admins.features.periods.data.remote.models.CreateAcademicPeriodRequestDto
import com.nistra.demy.admins.features.periods.data.remote.models.UpdateAcademicPeriodRequestDto
import javax.inject.Inject

class AcademicPeriodRemoteDataSourceImpl @Inject constructor(
    private val api: AcademicPeriodApi
) : AcademicPeriodRemoteDataSource {
    override suspend fun fetchAllPeriods(): List<AcademicPeriodResourceDto> {
        return safeApiCall(endpoint = "academic-periods") {
            api.getAllPeriods()
        }
    }

    override suspend fun getPeriodById(id: Long): AcademicPeriodResourceDto {
        return safeApiCall(endpoint = "academic-periods/$id"){
            api.getPeriodById(id)
        }
    }

    override suspend fun createAcademicPeriod(request: CreateAcademicPeriodRequestDto): AcademicPeriodResourceDto {
        return safeApiCall(endpoint = "academic-periods"){
            api.createPeriod(request)
        }
    }

    override suspend fun updateAcademicPeriod(id: Long, request: UpdateAcademicPeriodRequestDto): AcademicPeriodResourceDto {
        return safeApiCall(endpoint = "academic-periods/$id"){
            api.updatePeriod(id, request)
        }
    }

    override suspend fun deleteAcademicPeriod(id: Long) {
        safeApiCall(endpoint = "academic-periods/$id"){
            api.deletePeriod(id)
        }
    }


}
