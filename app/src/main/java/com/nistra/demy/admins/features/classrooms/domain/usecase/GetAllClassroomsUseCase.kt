package com.nistra.demy.admins.features.classrooms.domain.usecase

import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.classrooms.domain.repository.ClassroomRepository
import javax.inject.Inject


class GetAllClassroomsUseCase @Inject constructor(
    private val repository: ClassroomRepository
) {
    suspend operator fun invoke(): Result<List<Classroom>> {
        return runCatching {
            repository.getAllClassrooms()
        }
    }
}
