package com.nistra.demy.admins.features.courses.domain.repository

import com.nistra.demy.admins.features.courses.domain.models.Course

interface CourseRepository {
    suspend fun getAllCourses(): List<Course>

    suspend fun getCourseById(id: Long): Course

    suspend fun createCourse(course: Course): Course
    suspend fun updateCourse(course: Course): Course
    suspend fun deleteCourse(id: Long)
}
