package com.nistra.demy.admins.features.courses.data.datasource.remote

import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.courses.data.remote.api.CoursesApi
import com.nistra.demy.admins.features.courses.data.remote.models.CourseResourceDto
import com.nistra.demy.admins.features.courses.data.remote.models.CreateCourseRequestDto
import com.nistra.demy.admins.features.courses.data.remote.models.UpdateCourseRequestDto
import javax.inject.Inject

class CourseRemoteDataSourceImpl @Inject constructor(
    private val api: CoursesApi
) : CourseRemoteDataSource {
    override suspend fun fetchAllCourses(): List<CourseResourceDto> {
        return safeApiCall(endpoint = "courses") {
            api.getAllCourses()
        }
    }

    override suspend fun getCourseById(id: Long): CourseResourceDto {
        return safeApiCall(endpoint = "courses/$id") {
            api.getCourseById(id)
        }
    }

    override suspend fun createCourse(request: CreateCourseRequestDto): CourseResourceDto {
        return safeApiCall(endpoint = "courses") {
            api.createCourse(request)
        }
    }

    override suspend fun updateCourse(id: Long, request: UpdateCourseRequestDto): CourseResourceDto {
        return safeApiCall(endpoint = "courses/$id") {
            api.updateCourse(id, request)
        }
    }

    override suspend fun deleteCourse(id: Long) {
        return safeApiCall(endpoint = "courses/$id") {
            api.deleteCourse(id)
        }
    }
}
