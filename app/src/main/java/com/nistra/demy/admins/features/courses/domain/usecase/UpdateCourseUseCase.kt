package com.nistra.demy.admins.features.courses.domain.usecase

import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.courses.domain.repository.CourseRepository
import javax.inject.Inject

class UpdateCourseUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(course: Course): Result<Course> {
        return runCatching {
            repository.updateCourse(course)
        }
    }
}
