package com.nistra.demy.admins.features.courses.data.remote.api

import com.nistra.demy.admins.features.courses.data.remote.models.CourseResourceDto
import com.nistra.demy.admins.features.courses.data.remote.models.CreateCourseRequestDto
import com.nistra.demy.admins.features.courses.data.remote.models.UpdateCourseRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CoursesApi {

    @GET("courses")
    suspend fun getAllCourses(): List<CourseResourceDto>

    @GET("courses/{id}")
    suspend fun getCourseById(@Path("id") id: Long): CourseResourceDto

    @POST("courses")
    suspend fun createCourse(@Body request: CreateCourseRequestDto): CourseResourceDto

    @PUT("courses/{id}")
    suspend fun updateCourse(@Path("id") id: Long, @Body request: UpdateCourseRequestDto): CourseResourceDto

    @DELETE("courses/{id}")
    suspend fun deleteCourse(@Path("id") id: Long)
}
