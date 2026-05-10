package com.nistra.demy.admins.features.schedules.data.remote.api

import com.nistra.demy.admins.features.schedules.data.remote.models.*
import retrofit2.http.*

interface SchedulesApi {

    @GET("schedules")
    suspend fun getAllSchedules(): List<ScheduleResourceDto>

    @GET("schedules/{scheduleId}")
    suspend fun getScheduleById(@Path("scheduleId") scheduleId: Long): ScheduleResourceDto

    @GET("schedules/by-teacher/{teacherId}")
    suspend fun getClassSessionsByTeacherId(@Path("teacherId") teacherId: Long): List<ClassSessionResourceDto>

    @POST("schedules")
    suspend fun createSchedule(@Body request: CreateScheduleRequestDto): ScheduleResourceDto

    @PUT("schedules/{scheduleId}")
    suspend fun updateScheduleName(@Path("scheduleId") scheduleId: Long, @Body request: CreateScheduleRequestDto): ScheduleResourceDto

    @DELETE("schedules/{scheduleId}")
    suspend fun deleteSchedule(@Path("scheduleId") scheduleId: Long): Unit

    @POST("schedules/{scheduleId}/class-sessions")
    suspend fun addClassSessionToSchedule(@Path("scheduleId") scheduleId: Long, @Body request: AddClassSessionRequestDto): ScheduleResourceDto

    @DELETE("schedules/{scheduleId}/class-sessions/{classSessionId}")
    suspend fun removeClassSessionFromSchedule(@Path("scheduleId") scheduleId: Long, @Path("classSessionId") classSessionId: Long): ScheduleResourceDto
}
