package com.nistra.demy.admins.features.teachers.data.datasource.remote

import com.nistra.demy.admins.features.teachers.data.remote.dto.CreateTeacherRequestDto
import com.nistra.demy.admins.features.teachers.data.remote.dto.TeacherResourceDto

interface TeacherRemoteDataSource {
    suspend fun fetchTeachers(): List<TeacherResourceDto>

    suspend fun addTeacher(request: CreateTeacherRequestDto): TeacherResourceDto
}
