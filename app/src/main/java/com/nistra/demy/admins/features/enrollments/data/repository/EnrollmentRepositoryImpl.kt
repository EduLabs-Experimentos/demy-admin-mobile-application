package com.nistra.demy.admins.features.enrollments.data.repository

import com.nistra.demy.admins.features.enrollments.data.datasource.remote.EnrollmentRemoteDataSource
import com.nistra.demy.admins.features.enrollments.data.mapper.toCreateEnrollmentRequestDto
import com.nistra.demy.admins.features.enrollments.data.mapper.toDomain
import com.nistra.demy.admins.features.enrollments.data.mapper.toUpdateEnrollmentRequestDto
import com.nistra.demy.admins.features.enrollments.domain.model.Enrollment
import com.nistra.demy.admins.features.enrollments.domain.repository.EnrollmentRepository
import javax.inject.Inject

class EnrollmentRepositoryImpl @Inject constructor(
    private val remoteDataSource: EnrollmentRemoteDataSource
) : EnrollmentRepository {

    override suspend fun getAllEnrollments(): List<Enrollment> {
        val enrollmentsDto = remoteDataSource.getAllEnrollments()
        return enrollmentsDto.map { it.toDomain() }
    }

    override suspend fun getEnrollmentById(id: Long): Enrollment? {
        val enrollmentDto = remoteDataSource.getEnrollmentById(id)
        return enrollmentDto?.toDomain()
    }

    override suspend fun createEnrollment(enrollment: Enrollment): Enrollment {
        val requestDto = enrollment.toCreateEnrollmentRequestDto()
        val createdDto = remoteDataSource.createEnrollment(requestDto)
        return createdDto.toDomain()
    }

    override suspend fun updateEnrollment(enrollment: Enrollment): Enrollment {
        val requestDto = enrollment.toUpdateEnrollmentRequestDto()
        val updatedDto = remoteDataSource.updateEnrollment(enrollment.id, requestDto)
        return updatedDto.toDomain()
    }

    override suspend fun deleteEnrollment(id: Long) {
        remoteDataSource.deleteEnrollment(id)
    }
}
