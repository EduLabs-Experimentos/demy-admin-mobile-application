package com.nistra.demy.admins.features.classrooms.data.remote.api

import com.nistra.demy.admins.features.classrooms.data.remote.models.ClassroomResourceDto
import com.nistra.demy.admins.features.classrooms.data.remote.models.CreateClassroomRequestDto
import com.nistra.demy.admins.features.classrooms.data.remote.models.UpdateClassroomRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClassroomsApi {

    @GET("classrooms")
    suspend fun getAllClassrooms(): List<ClassroomResourceDto>

    @GET("classrooms/{classroomId}")
    suspend fun getClassroomById(@Path("classroomId") classroomId: Long): ClassroomResourceDto

    @POST("classrooms")
    suspend fun createClassroom(@Body request: CreateClassroomRequestDto): ClassroomResourceDto

    @PUT("classrooms/{classroomId}")
    suspend fun updateClassroom(@Path("classroomId") classroomId: Long, @Body request: UpdateClassroomRequestDto): ClassroomResourceDto

    @DELETE("classrooms/{classroomId}")
    suspend fun deleteClassroom(@Path("classroomId") classroomId: Long): Unit
}
