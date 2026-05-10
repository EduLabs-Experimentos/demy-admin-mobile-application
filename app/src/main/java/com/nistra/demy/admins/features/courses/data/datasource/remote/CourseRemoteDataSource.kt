package com.nistra.demy.admins.features.courses.data.datasource.remote

import com.nistra.demy.admins.features.courses.data.remote.models.CourseResourceDto
import com.nistra.demy.admins.features.courses.data.remote.models.CreateCourseRequestDto
import com.nistra.demy.admins.features.courses.data.remote.models.UpdateCourseRequestDto

interface CourseRemoteDataSource {

    suspend fun fetchAllCourses(): List<CourseResourceDto>

    suspend fun getCourseById(id: Long): CourseResourceDto

    suspend fun createCourse(request: CreateCourseRequestDto): CourseResourceDto

    suspend fun updateCourse(id: Long, request: UpdateCourseRequestDto): CourseResourceDto

    suspend fun deleteCourse(id: Long)
}
