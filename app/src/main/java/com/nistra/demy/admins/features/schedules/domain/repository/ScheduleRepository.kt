package com.nistra.demy.admins.features.schedules.domain.repository

import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.models.ClassSession


interface ScheduleRepository {
    suspend fun getAllSchedules(): List<Schedule>
    suspend fun getScheduleById(id: Long): Schedule?
    suspend fun getClassSessionsByTeacherId(teacherId: Long): List<ClassSession>

    suspend fun createSchedule(name: String): Schedule?
    suspend fun updateScheduleName(id: Long, name: String): Schedule?
    suspend fun deleteSchedule(id: Long)

    suspend fun addClassSessionToSchedule(
        scheduleId: Long,
        startTime: String,
        endTime: String,
        dayOfWeek: String,
        courseId: Long,
        classroomId: Long,
        teacherFirstName: String,
        teacherLastName: String
    ): Schedule?

    suspend fun removeClassSessionFromSchedule(scheduleId: Long, classSessionId: Long): Schedule?
}
