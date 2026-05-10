package com.nistra.demy.admins.features.teachers.data.remote.api

import com.nistra.demy.admins.features.teachers.data.remote.dto.CreateTeacherRequestDto
import com.nistra.demy.admins.features.teachers.data.remote.dto.TeacherResourceDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TeachersApi {
    @GET("teachers")
    suspend fun getAllTeachers(): List<TeacherResourceDto>

    @POST("teachers")
    suspend fun createTeacher(@Body request: CreateTeacherRequestDto): TeacherResourceDto
}
