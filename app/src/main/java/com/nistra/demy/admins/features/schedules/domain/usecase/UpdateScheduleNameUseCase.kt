package com.nistra.demy.admins.features.schedules.domain.usecase

import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.repository.ScheduleRepository
import javax.inject.Inject

class UpdateScheduleNameUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    suspend operator fun invoke(id: Long, name: String): Result<Schedule?> {
        return runCatching {
            repository.updateScheduleName(id, name)
        }
    }
}
