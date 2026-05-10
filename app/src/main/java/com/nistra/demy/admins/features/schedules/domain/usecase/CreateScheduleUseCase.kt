package com.nistra.demy.admins.features.schedules.domain.usecase

import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.repository.ScheduleRepository
import javax.inject.Inject

class CreateScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    suspend operator fun invoke(name: String): Result<Schedule?> {
        return runCatching {
            repository.createSchedule(name)
        }
    }
}
