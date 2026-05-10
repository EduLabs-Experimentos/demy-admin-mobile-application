package com.nistra.demy.admins.features.classrooms.data.repository
import com.nistra.demy.admins.features.classrooms.data.datasource.remote.ClassroomRemoteDataSource
import com.nistra.demy.admins.features.classrooms.data.mapper.toCreateRequestDto
import com.nistra.demy.admins.features.classrooms.data.mapper.toDomain
import com.nistra.demy.admins.features.classrooms.data.mapper.toUpdateRequestDto
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.classrooms.domain.repository.ClassroomRepository
import javax.inject.Inject

class ClassroomRepositoryImpl @Inject constructor(
    private val classroomRemoteDatasource: ClassroomRemoteDataSource
) : ClassroomRepository {

    override suspend fun getAllClassrooms(): List<Classroom> {
        val classroomsResponse = classroomRemoteDatasource.fetchAllClassrooms()
        return classroomsResponse.map { it.toDomain() }
    }

    override suspend fun getClassroomById(id: Long): Classroom {
        return classroomRemoteDatasource.getClassroomById(id).toDomain()
    }

    override suspend fun createClassroom(classroom: Classroom): Classroom {
        val createdClassroomResourceDto = classroomRemoteDatasource.createClassroom(classroom.toCreateRequestDto())
        return createdClassroomResourceDto.toDomain()
    }

    override suspend fun updateClassroom(classroom: Classroom): Classroom {
        val updatedClassroomResourceDto = classroomRemoteDatasource.updateClassroom(classroom.id,classroom.toUpdateRequestDto())
        return updatedClassroomResourceDto.toDomain()
    }

    override suspend fun deleteClassroom(id: Long) {
        classroomRemoteDatasource.deleteClassroom(id)
    }
}
