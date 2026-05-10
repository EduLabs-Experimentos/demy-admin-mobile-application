package com.nistra.demy.admins.features.teachers.data.repository

import com.nistra.demy.admins.features.teachers.data.datasource.remote.TeacherRemoteDataSource
import com.nistra.demy.admins.features.teachers.data.mapper.toDomain
import com.nistra.demy.admins.features.teachers.data.mapper.toRequestDto
import com.nistra.demy.admins.features.teachers.domain.model.Teacher
import com.nistra.demy.admins.features.teachers.domain.repository.TeacherRepository
import javax.inject.Inject

class TeacherRepositoryImpl @Inject constructor(
    private val teachersRemoteDataSource: TeacherRemoteDataSource
) : TeacherRepository {
    override suspend fun getAllTeachers(): List<Teacher> {
        val teachersResponse = teachersRemoteDataSource.fetchTeachers()
        return teachersResponse.map { it.toDomain() }
    }

    override suspend fun createTeacher(teacher: Teacher): Teacher {
        val createdTeacherResourceDto = teachersRemoteDataSource.addTeacher(teacher.toRequestDto())
        return createdTeacherResourceDto.toDomain()
    }
}
