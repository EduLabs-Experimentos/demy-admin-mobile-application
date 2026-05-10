package com.nistra.demy.admins.features.teachers.domain.repository

import com.nistra.demy.admins.features.teachers.domain.model.Teacher

interface TeacherRepository {
    suspend fun getAllTeachers(): List<Teacher>

    suspend fun createTeacher(teacher: Teacher): Teacher
}
