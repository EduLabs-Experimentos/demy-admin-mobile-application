package com.nistra.demy.admins.features.schedules.data.repository
import com.nistra.demy.admins.features.schedules.data.datasource.remote.ScheduleRemoteDataSource
import com.nistra.demy.admins.features.schedules.data.mapper.toCreateScheduleRequestDto
import com.nistra.demy.admins.features.schedules.data.mapper.toDomain
import com.nistra.demy.admins.features.schedules.data.remote.models.AddClassSessionRequestDto
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.models.ClassSession
import com.nistra.demy.admins.features.schedules.domain.repository.ScheduleRepository
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val remoteDataSource: ScheduleRemoteDataSource
) : ScheduleRepository {

    override suspend fun getAllSchedules(): List<Schedule> {
        val schedulesDto = remoteDataSource.getAllSchedules()
        return schedulesDto.mapNotNull { it.toDomain() }
    }

    override suspend fun getScheduleById(id: Long): Schedule? {
        val scheduleDto = remoteDataSource.getScheduleById(id)
        return scheduleDto.toDomain()
    }

    override suspend fun getClassSessionsByTeacherId(teacherId: Long): List<ClassSession> {
        val classSessionsDto = remoteDataSource.getClassSessionsByTeacherId(teacherId)
        return classSessionsDto.mapNotNull { it.toDomain() }
    }

    override suspend fun createSchedule(name: String): Schedule? {
        val requestDto = name.toCreateScheduleRequestDto()
        val createdScheduleDto = remoteDataSource.createSchedule(requestDto)
        return createdScheduleDto.toDomain()
    }

    override suspend fun updateScheduleName(id: Long, name: String): Schedule? {
        val requestDto = name.toCreateScheduleRequestDto()
        val updatedScheduleDto = remoteDataSource.updateScheduleName(id, requestDto)
        return updatedScheduleDto.toDomain()
    }

    override suspend fun deleteSchedule(id: Long) {
        remoteDataSource.deleteSchedule(id)
    }

    override suspend fun addClassSessionToSchedule(
        scheduleId: Long,
        startTime: String,
        endTime: String,
        dayOfWeek: String,
        courseId: Long,
        classroomId: Long,
        teacherFirstName: String,
        teacherLastName: String
    ): Schedule? {
        val requestDto = AddClassSessionRequestDto(
            startTime = startTime,
            endTime = endTime,
            dayOfWeek = dayOfWeek,
            courseId = courseId,
            classroomId = classroomId,
            teacherFirstName = teacherFirstName,
            teacherLastName = teacherLastName
        )

        val updatedScheduleDto = remoteDataSource.addClassSessionToSchedule(scheduleId, requestDto)
        return updatedScheduleDto.toDomain()
    }

    override suspend fun removeClassSessionFromSchedule(scheduleId: Long, classSessionId: Long): Schedule? {
        val updatedScheduleDto = remoteDataSource.removeClassSessionFromSchedule(scheduleId, classSessionId)
        return updatedScheduleDto.toDomain()
    }
}
