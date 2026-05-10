package com.nistra.demy.admins.features.classrooms.domain.usecase

import com.nistra.demy.admins.features.classrooms.domain.repository.ClassroomRepository
import javax.inject.Inject


class DeleteClassroomUseCase @Inject constructor(
    private val repository: ClassroomRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> {
        return runCatching {
            repository.deleteClassroom(id)
        }
    }
}
