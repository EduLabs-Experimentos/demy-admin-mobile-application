package com.nistra.demy.admins.features.enrollments.domain.repository

import com.nistra.demy.admins.features.enrollments.domain.model.Enrollment

interface EnrollmentRepository {

    suspend fun getAllEnrollments(): List<Enrollment>

    suspend fun getEnrollmentById(id: Long): Enrollment?

    suspend fun createEnrollment(enrollment: Enrollment): Enrollment

    suspend fun updateEnrollment(enrollment: Enrollment): Enrollment

    suspend fun deleteEnrollment(id: Long)
}
