package com.nistra.demy.admins.features.students.domain.repository

import com.nistra.demy.admins.features.students.domain.model.Student

interface StudentRepository {

    suspend fun getAllStudents(): List<Student>

    suspend fun getStudentById(id: Long): Student

    suspend fun createStudent(student: Student): Student

    suspend fun updateStudent(student: Student): Student

    suspend fun deleteStudent(id: Long)
}
