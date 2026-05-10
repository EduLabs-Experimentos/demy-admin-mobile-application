package com.nistra.demy.admins.features.courses.domain.usecase

import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.courses.domain.repository.CourseRepository
import javax.inject.Inject

class GetAllCoursesUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(): Result<List<Course>> {
        return runCatching {
            repository.getAllCourses()
        }
    }
}
