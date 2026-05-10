package com.nistra.demy.admins.features.enrollments.domain.usecase

import com.nistra.demy.admins.features.enrollments.domain.model.Enrollment
import com.nistra.demy.admins.features.enrollments.domain.repository.EnrollmentRepository
import javax.inject.Inject

class GetAllEnrollmentsUseCase @Inject constructor(
    private val repository: EnrollmentRepository
) {
    suspend operator fun invoke(): Result<List<Enrollment>> {
        return runCatching {
            repository.getAllEnrollments()
        }
    }
}
