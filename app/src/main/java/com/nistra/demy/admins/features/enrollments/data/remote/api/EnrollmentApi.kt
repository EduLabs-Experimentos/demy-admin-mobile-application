package com.nistra.demy.admins.features.enrollments.data.remote.api

import com.nistra.demy.admins.features.enrollments.data.remote.models.CreateEnrollmentRequestDto
import com.nistra.demy.admins.features.enrollments.data.remote.models.EnrollmentResourceDto
import com.nistra.demy.admins.features.enrollments.data.remote.models.UpdateEnrollmentRequestDto
import retrofit2.http.*

interface EnrollmentApi {

    @GET("enrollments")
    suspend fun getAllEnrollments(): List<EnrollmentResourceDto>

    @GET("enrollments/{id}")
    suspend fun getEnrollmentById(@Path("id") id: Long): EnrollmentResourceDto

    @POST("enrollments")
    suspend fun createEnrollment(
        @Body request: CreateEnrollmentRequestDto
    ): EnrollmentResourceDto

    @PUT("enrollments/{id}")
    suspend fun updateEnrollment(
        @Path("id") id: Long,
        @Body request: UpdateEnrollmentRequestDto
    ): EnrollmentResourceDto

    @DELETE("enrollments/{id}")
    suspend fun deleteEnrollment(@Path("id") id: Long)
}
