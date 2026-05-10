package com.nistra.demy.admins.features.enrollments.domain.usecase

import com.nistra.demy.admins.features.enrollments.domain.model.Enrollment
import com.nistra.demy.admins.features.enrollments.domain.repository.EnrollmentRepository
import javax.inject.Inject

class UpdateEnrollmentUseCase @Inject constructor(
    private val repository: EnrollmentRepository
) {
    suspend operator fun invoke(enrollment: Enrollment): Result<Enrollment> {
        return runCatching {
            repository.updateEnrollment(enrollment)
        }
    }
}
