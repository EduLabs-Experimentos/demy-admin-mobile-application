package com.nistra.demy.admins.features.students.domain.usecase

import com.nistra.demy.admins.features.students.domain.model.Student
import com.nistra.demy.admins.features.students.domain.repository.StudentRepository
import javax.inject.Inject

class CreateStudentUseCase @Inject constructor(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(student: Student): Result<Student> {
        return runCatching {
            repository.createStudent(student)
        }
    }
}
