package com.nistra.demy.admins.features.teachers.data.mapper

import com.nistra.demy.admins.features.teachers.data.remote.dto.CreateTeacherRequestDto
import com.nistra.demy.admins.features.teachers.data.remote.dto.TeacherResourceDto
import com.nistra.demy.admins.features.teachers.domain.model.Teacher

fun TeacherResourceDto.toDomain(): Teacher {
    return Teacher(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        countryCode = this.countryCode,
        phone = this.phone
    )
}

fun Teacher.toRequestDto(): CreateTeacherRequestDto {
    return CreateTeacherRequestDto(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        countryCode = countryCode,
        phone = this.phone
    )
}
