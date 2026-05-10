package com.nistra.demy.admins.features.classrooms.data.datasource.remote

import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.classrooms.data.remote.api.ClassroomsApi
import com.nistra.demy.admins.features.classrooms.data.remote.models.ClassroomResourceDto
import com.nistra.demy.admins.features.classrooms.data.remote.models.CreateClassroomRequestDto
import com.nistra.demy.admins.features.classrooms.data.remote.models.UpdateClassroomRequestDto
import javax.inject.Inject

class ClassroomRemoteDataSourceImpl @Inject constructor(
    private val api: ClassroomsApi
) : ClassroomRemoteDataSource {
    override suspend fun fetchAllClassrooms(): List<ClassroomResourceDto> {
        return safeApiCall(endpoint = "classrooms") {
            api.getAllClassrooms()
        }
    }

    override suspend fun getClassroomById(id: Long): ClassroomResourceDto {
        return safeApiCall(endpoint = "classrooms/$id") {
            api.getClassroomById(id)
        }
    }

    override suspend fun createClassroom(request: CreateClassroomRequestDto): ClassroomResourceDto {
        return safeApiCall(endpoint = "classrooms") {
            api.createClassroom(request)
        }
    }

    override suspend fun updateClassroom(id: Long, request: UpdateClassroomRequestDto): ClassroomResourceDto {
        return safeApiCall(endpoint = "classrooms/$id") {
            api.updateClassroom(id, request)
        }
    }

    override suspend fun deleteClassroom(id: Long) {
        return safeApiCall(endpoint = "classrooms/$id") {
            api.deleteClassroom(id)
        }
    }
}
