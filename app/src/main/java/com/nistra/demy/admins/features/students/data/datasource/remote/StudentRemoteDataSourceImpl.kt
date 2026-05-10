package com.nistra.demy.admins.features.students.data.datasource.remote

import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.students.data.remote.api.StudentApi
import com.nistra.demy.admins.features.students.data.remote.models.CreateStudentRequestDto
import com.nistra.demy.admins.features.students.data.remote.models.StudentResourceDto
import com.nistra.demy.admins.features.students.data.remote.models.UpdateStudentRequestDto
import javax.inject.Inject

class StudentRemoteDataSourceImpl @Inject constructor(
    private val api: StudentApi
) : StudentRemoteDataSource {

    override suspend fun fetchAllStudents(): List<StudentResourceDto> {
        return safeApiCall(endpoint = "students") {
            api.getAllStudents()
        }
    }

    override suspend fun getStudentById(id: Long): StudentResourceDto {
        return safeApiCall(endpoint = "students/$id") {
            api.getStudentById(id)
        }
    }

    override suspend fun createStudent(request: CreateStudentRequestDto): StudentResourceDto {
        return safeApiCall(endpoint = "students") {
            api.createStudent(request)
        }
    }

    override suspend fun updateStudent(id: Long, request: UpdateStudentRequestDto): StudentResourceDto {
        return safeApiCall(endpoint = "students/$id") {
            api.updateStudent(id, request)
        }
    }

    override suspend fun deleteStudent(id: Long) {
        return safeApiCall(endpoint = "students/$id") {
            api.deleteStudent(id)
        }
    }
}
