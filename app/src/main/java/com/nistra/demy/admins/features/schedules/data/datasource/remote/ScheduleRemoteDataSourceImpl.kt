package com.nistra.demy.admins.features.schedules.data.datasource.remote

import com.nistra.demy.admins.features.schedules.data.remote.api.SchedulesApi
import com.nistra.demy.admins.features.schedules.data.remote.models.AddClassSessionRequestDto
import com.nistra.demy.admins.features.schedules.data.remote.models.ClassSessionResourceDto
import com.nistra.demy.admins.features.schedules.data.remote.models.CreateScheduleRequestDto
import com.nistra.demy.admins.features.schedules.data.remote.models.ScheduleResourceDto
import javax.inject.Inject

class ScheduleRemoteDataSourceImpl @Inject constructor(
    private val api: SchedulesApi
) : ScheduleRemoteDataSource {

    override suspend fun getAllSchedules(): List<ScheduleResourceDto> {
        return api.getAllSchedules()
    }

    override suspend fun getScheduleById(id: Long): ScheduleResourceDto {
        return api.getScheduleById(id)
    }

    override suspend fun getClassSessionsByTeacherId(teacherId: Long): List<ClassSessionResourceDto> {
        return api.getClassSessionsByTeacherId(teacherId)
    }

    override suspend fun createSchedule(request: CreateScheduleRequestDto): ScheduleResourceDto {
        return api.createSchedule(request)
    }

    override suspend fun updateScheduleName(id: Long, request: CreateScheduleRequestDto): ScheduleResourceDto {
        return api.updateScheduleName(id, request)
    }

    override suspend fun deleteSchedule(id: Long): Unit {
        return api.deleteSchedule(id)
    }

    override suspend fun addClassSessionToSchedule(scheduleId: Long, request: AddClassSessionRequestDto): ScheduleResourceDto {
        return api.addClassSessionToSchedule(scheduleId, request)
    }

    override suspend fun removeClassSessionFromSchedule(scheduleId: Long, classSessionId: Long): ScheduleResourceDto {
        return api.removeClassSessionFromSchedule(scheduleId, classSessionId)
    }
}
