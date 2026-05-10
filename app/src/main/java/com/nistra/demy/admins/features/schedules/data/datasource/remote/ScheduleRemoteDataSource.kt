package com.nistra.demy.admins.features.schedules.data.datasource.remote

import com.nistra.demy.admins.features.schedules.data.remote.models.AddClassSessionRequestDto
import com.nistra.demy.admins.features.schedules.data.remote.models.ClassSessionResourceDto
import com.nistra.demy.admins.features.schedules.data.remote.models.CreateScheduleRequestDto
import com.nistra.demy.admins.features.schedules.data.remote.models.ScheduleResourceDto

interface ScheduleRemoteDataSource {
    suspend fun getAllSchedules(): List<ScheduleResourceDto>
    suspend fun getScheduleById(id: Long): ScheduleResourceDto
    suspend fun getClassSessionsByTeacherId(teacherId: Long): List<ClassSessionResourceDto>

    suspend fun createSchedule(request: CreateScheduleRequestDto): ScheduleResourceDto
    suspend fun updateScheduleName(id: Long, request: CreateScheduleRequestDto): ScheduleResourceDto
    suspend fun deleteSchedule(id: Long): Unit

    suspend fun addClassSessionToSchedule(scheduleId: Long, request: AddClassSessionRequestDto): ScheduleResourceDto
    suspend fun removeClassSessionFromSchedule(scheduleId: Long, classSessionId: Long): ScheduleResourceDto
}
