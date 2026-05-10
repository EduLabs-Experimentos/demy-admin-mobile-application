package com.nistra.demy.admins.features.enrollments.domain.usecase

import com.nistra.demy.admins.features.enrollments.domain.repository.EnrollmentRepository
import javax.inject.Inject

class DeleteEnrollmentUseCase @Inject constructor(
    private val repository: EnrollmentRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> {
        return runCatching {
            repository.deleteEnrollment(id)
        }
    }
}
