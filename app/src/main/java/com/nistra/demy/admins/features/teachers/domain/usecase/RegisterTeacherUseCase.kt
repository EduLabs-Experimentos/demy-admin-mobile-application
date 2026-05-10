package com.nistra.demy.admins.features.teachers.domain.usecase

import com.nistra.demy.admins.features.teachers.domain.model.Teacher
import com.nistra.demy.admins.features.teachers.domain.repository.TeacherRepository
import javax.inject.Inject

class RegisterTeacherUseCase @Inject constructor(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(teacher: Teacher): Result<Teacher> {
        return runCatching { repository.createTeacher(teacher) }
    }
}
