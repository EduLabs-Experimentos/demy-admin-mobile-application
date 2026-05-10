package com.nistra.demy.admins.features.students.data.remote.api

import com.nistra.demy.admins.features.students.data.remote.models.CreateStudentRequestDto
import com.nistra.demy.admins.features.students.data.remote.models.StudentResourceDto
import com.nistra.demy.admins.features.students.data.remote.models.UpdateStudentRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface StudentApi {

    @GET("students")
    suspend fun getAllStudents(): List<StudentResourceDto>

    @GET("students/{id}")
    suspend fun getStudentById(@Path("id") id: Long): StudentResourceDto

    @POST("students")
    suspend fun createStudent(@Body request: CreateStudentRequestDto): StudentResourceDto

    @PUT("students/{id}")
    suspend fun updateStudent(
        @Path("id") id: Long,
        @Body request: UpdateStudentRequestDto
    ): StudentResourceDto

    @DELETE("students/{id}")
    suspend fun deleteStudent(@Path("id") id: Long)
}
