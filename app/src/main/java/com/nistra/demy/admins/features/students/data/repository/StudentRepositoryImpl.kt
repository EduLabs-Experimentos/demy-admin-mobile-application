package com.nistra.demy.admins.features.students.data.repository

import com.nistra.demy.admins.features.students.data.datasource.remote.StudentRemoteDataSource
import com.nistra.demy.admins.features.students.data.mapper.toCreateRequestDto
import com.nistra.demy.admins.features.students.data.mapper.toDomain
import com.nistra.demy.admins.features.students.data.mapper.toUpdateRequestDto
import com.nistra.demy.admins.features.students.domain.model.Student
import com.nistra.demy.admins.features.students.domain.repository.StudentRepository
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val studentRemoteDataSource: StudentRemoteDataSource
) : StudentRepository {

    override suspend fun getAllStudents(): List<Student> {
        val studentsResponse = studentRemoteDataSource.fetchAllStudents()
        return studentsResponse.map { it.toDomain() }
    }

    override suspend fun getStudentById(id: Long): Student {
        return studentRemoteDataSource.getStudentById(id).toDomain()
    }

    override suspend fun createStudent(student: Student): Student {
        val createdStudentResourceDto =
            studentRemoteDataSource.createStudent(student.toCreateRequestDto())
        return createdStudentResourceDto.toDomain()
    }

    override suspend fun updateStudent(student: Student): Student {
        val updatedStudentResourceDto =
            studentRemoteDataSource.updateStudent(student.id, student.toUpdateRequestDto())
        return updatedStudentResourceDto.toDomain()
    }

    override suspend fun deleteStudent(id: Long) {
        studentRemoteDataSource.deleteStudent(id)
    }
}
