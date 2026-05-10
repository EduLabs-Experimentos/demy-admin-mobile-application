package com.nistra.demy.admins.features.classrooms.domain.usecase

import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.classrooms.domain.repository.ClassroomRepository
import javax.inject.Inject


class UpdateClassroomUseCase @Inject constructor(
    private val repository: ClassroomRepository
) {
    suspend operator fun invoke(classroom: Classroom): Result<Classroom> {
        return runCatching {
            repository.updateClassroom(classroom)
        }
    }
}
