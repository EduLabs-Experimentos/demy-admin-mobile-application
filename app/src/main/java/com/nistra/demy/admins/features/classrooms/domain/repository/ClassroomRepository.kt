package com.nistra.demy.admins.features.classrooms.domain.repository

import com.nistra.demy.admins.features.classrooms.domain.models.Classroom

interface ClassroomRepository {

    suspend fun getAllClassrooms(): List<Classroom>
    suspend fun getClassroomById(id: Long): Classroom

    suspend fun createClassroom(classroom: Classroom): Classroom
    suspend fun updateClassroom(classroom: Classroom): Classroom
    suspend fun deleteClassroom(id: Long)
}
