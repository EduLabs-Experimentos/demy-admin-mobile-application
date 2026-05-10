package com.nistra.demy.admins.features.teachers.data.datasource.remote

import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.teachers.data.remote.api.TeachersApi
import com.nistra.demy.admins.features.teachers.data.remote.dto.CreateTeacherRequestDto
import com.nistra.demy.admins.features.teachers.data.remote.dto.TeacherResourceDto
import javax.inject.Inject

class TeacherRemoteDataSourceImpl @Inject constructor(
    private val api : TeachersApi
) : TeacherRemoteDataSource {
    override suspend fun fetchTeachers(): List<TeacherResourceDto> {
        return safeApiCall(endpoint = "teachers") { api.getAllTeachers() }
    }

    override suspend fun addTeacher(request: CreateTeacherRequestDto): TeacherResourceDto {
        return safeApiCall(endpoint = "teachers") { api.createTeacher(request) }
    }
}
