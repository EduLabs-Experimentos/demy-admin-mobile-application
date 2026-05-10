package com.nistra.demy.admins.features.students.domain.usecase

import com.nistra.demy.admins.features.students.domain.model.Student
import com.nistra.demy.admins.features.students.domain.repository.StudentRepository
import javax.inject.Inject

class GetAllStudentsUseCase @Inject constructor(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(): Result<List<Student>> {
        return runCatching {
            repository.getAllStudents()
        }
    }
}
