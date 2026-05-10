package com.nistra.demy.admins.features.students.data.datasource.remote

import com.nistra.demy.admins.features.students.data.remote.models.CreateStudentRequestDto
import com.nistra.demy.admins.features.students.data.remote.models.StudentResourceDto
import com.nistra.demy.admins.features.students.data.remote.models.UpdateStudentRequestDto

interface StudentRemoteDataSource {

    suspend fun fetchAllStudents(): List<StudentResourceDto>

    suspend fun getStudentById(id: Long): StudentResourceDto

    suspend fun createStudent(request: CreateStudentRequestDto): StudentResourceDto

    suspend fun updateStudent(id: Long, request: UpdateStudentRequestDto): StudentResourceDto

    suspend fun deleteStudent(id: Long)
}
