package com.nistra.demy.admins.features.schedules.domain.usecase

import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.repository.ScheduleRepository
import javax.inject.Inject


class AddClassSessionToScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    @Suppress("LongParameterList")
    suspend operator fun invoke(
        scheduleId: Long,
        startTime: String,
        endTime: String,
        dayOfWeek: String,
        courseId: Long,
        classroomId: Long,
        teacherFirstName: String,
        teacherLastName: String
    ): Result<Schedule?> {
        return runCatching {
            repository.addClassSessionToSchedule(
                scheduleId,
                startTime,
                endTime,
                dayOfWeek,
                courseId,
                classroomId,
                teacherFirstName,
                teacherLastName
            )
        }
    }
}
