package com.nistra.demy.admins.features.schedules.domain.usecase

import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.repository.ScheduleRepository
import javax.inject.Inject

class RemoveClassSessionFromScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    suspend operator fun invoke(scheduleId: Long, classSessionId: Long): Result<Schedule?> {
        return runCatching {
            repository.removeClassSessionFromSchedule(scheduleId, classSessionId)
        }
    }
}
