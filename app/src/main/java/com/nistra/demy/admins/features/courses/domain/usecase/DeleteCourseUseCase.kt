package com.nistra.demy.admins.features.courses.domain.usecase

import com.nistra.demy.admins.features.courses.domain.repository.CourseRepository
import javax.inject.Inject

class DeleteCourseUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> {
        return runCatching {
            repository.deleteCourse(id)
        }
    }
}
