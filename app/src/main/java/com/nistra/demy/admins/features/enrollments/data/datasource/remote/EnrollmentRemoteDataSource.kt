package com.nistra.demy.admins.features.enrollments.data.datasource.remote

import com.nistra.demy.admins.features.enrollments.data.remote.models.CreateEnrollmentRequestDto
import com.nistra.demy.admins.features.enrollments.data.remote.models.EnrollmentResourceDto
import com.nistra.demy.admins.features.enrollments.data.remote.models.UpdateEnrollmentRequestDto

interface EnrollmentRemoteDataSource {

    suspend fun getAllEnrollments(): List<EnrollmentResourceDto>

    suspend fun getEnrollmentById(id: Long): EnrollmentResourceDto?

    suspend fun createEnrollment(request: CreateEnrollmentRequestDto): EnrollmentResourceDto

    suspend fun updateEnrollment(id: Long, request: UpdateEnrollmentRequestDto): EnrollmentResourceDto

    suspend fun deleteEnrollment(id: Long)
}
