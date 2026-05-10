package com.nistra.demy.admins.features.classrooms.data.datasource.remote

import com.nistra.demy.admins.features.classrooms.data.remote.models.ClassroomResourceDto
import com.nistra.demy.admins.features.classrooms.data.remote.models.CreateClassroomRequestDto
import com.nistra.demy.admins.features.classrooms.data.remote.models.UpdateClassroomRequestDto

interface ClassroomRemoteDataSource {

    suspend fun fetchAllClassrooms(): List<ClassroomResourceDto>

    suspend fun getClassroomById(id: Long): ClassroomResourceDto

    suspend fun createClassroom(request: CreateClassroomRequestDto): ClassroomResourceDto

    suspend fun updateClassroom(id: Long, request: UpdateClassroomRequestDto): ClassroomResourceDto

    suspend fun deleteClassroom(id: Long)
}
