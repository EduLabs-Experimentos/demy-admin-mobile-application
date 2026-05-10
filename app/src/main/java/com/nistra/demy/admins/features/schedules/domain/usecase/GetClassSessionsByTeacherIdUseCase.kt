package com.nistra.demy.admins.features.schedules.domain.usecase

import com.nistra.demy.admins.features.schedules.domain.models.ClassSession
import com.nistra.demy.admins.features.schedules.domain.repository.ScheduleRepository
import javax.inject.Inject

class GetClassSessionsByTeacherIdUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    suspend operator fun invoke(teacherId: Long): Result<List<ClassSession>> {
        return runCatching {
            repository.getClassSessionsByTeacherId(teacherId)
        }
    }
}
