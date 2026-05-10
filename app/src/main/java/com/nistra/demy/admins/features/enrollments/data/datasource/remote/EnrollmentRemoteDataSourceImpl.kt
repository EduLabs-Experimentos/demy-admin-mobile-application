package com.nistra.demy.admins.features.enrollments.data.datasource.remote

import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.enrollments.data.remote.api.EnrollmentApi
import com.nistra.demy.admins.features.enrollments.data.remote.models.CreateEnrollmentRequestDto
import com.nistra.demy.admins.features.enrollments.data.remote.models.EnrollmentResourceDto
import com.nistra.demy.admins.features.enrollments.data.remote.models.UpdateEnrollmentRequestDto
import javax.inject.Inject

class EnrollmentRemoteDataSourceImpl @Inject constructor(
    private val api: EnrollmentApi
) : EnrollmentRemoteDataSource {

    override suspend fun getAllEnrollments(): List<EnrollmentResourceDto> {
        return safeApiCall(endpoint = "enrollments") {
            api.getAllEnrollments()
        }
    }

    override suspend fun getEnrollmentById(id: Long): EnrollmentResourceDto? {
        return safeApiCall(endpoint = "enrollments/$id") {
            api.getEnrollmentById(id)
        }
    }

    override suspend fun createEnrollment(request: CreateEnrollmentRequestDto): EnrollmentResourceDto {
        return safeApiCall(endpoint = "enrollments") {
            api.createEnrollment(request)
        }
    }

    override suspend fun updateEnrollment(id: Long, request: UpdateEnrollmentRequestDto): EnrollmentResourceDto {
        return safeApiCall(endpoint = "enrollments/$id") {
            api.updateEnrollment(id, request)
        }
    }

    override suspend fun deleteEnrollment(id: Long) {
        return safeApiCall(endpoint = "enrollments/$id") {
            api.deleteEnrollment(id)
        }
    }
}
