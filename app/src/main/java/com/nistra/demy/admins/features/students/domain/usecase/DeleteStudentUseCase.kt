package com.nistra.demy.admins.features.students.domain.usecase

import com.nistra.demy.admins.features.students.domain.repository.StudentRepository
import javax.inject.Inject

class DeleteStudentUseCase @Inject constructor(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> {
        return runCatching {
            repository.deleteStudent(id)
        }
    }
}
