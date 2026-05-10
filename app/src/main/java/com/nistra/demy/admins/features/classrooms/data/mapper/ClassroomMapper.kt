package com.nistra.demy.admins.features.classrooms.data.mapper

import com.nistra.demy.admins.features.classrooms.data.remote.models.ClassroomResourceDto
import com.nistra.demy.admins.features.classrooms.data.remote.models.CreateClassroomRequestDto
import com.nistra.demy.admins.features.classrooms.data.remote.models.UpdateClassroomRequestDto
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom

fun ClassroomResourceDto.toDomain(): Classroom {
    return Classroom(
            id = id,
            code = code,
            capacity = capacity,
            campus = campus
    )
}

fun Classroom.toCreateRequestDto(): CreateClassroomRequestDto {
    return CreateClassroomRequestDto(
        code = this.code,
        capacity = this.capacity,
        campus = this.campus
    )
}

fun Classroom.toUpdateRequestDto(): UpdateClassroomRequestDto {
    return UpdateClassroomRequestDto(
        code = this.code,
        capacity = this.capacity,
        campus = this.campus
    )
}
