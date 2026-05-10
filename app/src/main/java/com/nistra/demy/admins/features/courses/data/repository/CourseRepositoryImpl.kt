package com.nistra.demy.admins.features.courses.data.repository

import com.nistra.demy.admins.features.courses.data.datasource.remote.CourseRemoteDataSource
import com.nistra.demy.admins.features.courses.data.mapper.toCreateRequestDto
import com.nistra.demy.admins.features.courses.data.mapper.toDomain
import com.nistra.demy.admins.features.courses.data.mapper.toUpdateRequestDto
import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.courses.domain.repository.CourseRepository
import javax.inject.Inject


class CourseRepositoryImpl @Inject constructor(
    private val courseRemoteDataSource: CourseRemoteDataSource
) : CourseRepository {

    override suspend fun getAllCourses(): List<Course> {
        val coursesResponse = courseRemoteDataSource.fetchAllCourses()
        return coursesResponse.map { it.toDomain() }
    }

    override suspend fun getCourseById(id: Long): Course {
        return courseRemoteDataSource.getCourseById(id).toDomain()
    }

    override suspend fun createCourse(course: Course): Course {
        val createdCourseResourceDto = courseRemoteDataSource.createCourse(course.toCreateRequestDto())
        return createdCourseResourceDto.toDomain()
    }

    override suspend fun updateCourse(course: Course): Course {
        val updatedCourseResourceDto = courseRemoteDataSource.updateCourse(course.id,course.toUpdateRequestDto())
        return updatedCourseResourceDto.toDomain()
    }

    override suspend fun deleteCourse(id: Long) {
        courseRemoteDataSource.deleteCourse(id)
    }
}
