package com.nistra.demy.admins.features.students.data.mapper

import com.nistra.demy.admins.features.students.data.remote.models.CreateStudentRequestDto
import com.nistra.demy.admins.features.students.data.remote.models.StudentResourceDto
import com.nistra.demy.admins.features.students.data.remote.models.UpdateStudentRequestDto
import com.nistra.demy.admins.features.students.domain.model.Student

fun StudentResourceDto.toDomain(): Student {
    return Student(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        dni = this.dni,
        emailAddress = this.email,
        sex = this.sex,
        birthDate = this.birthDate,
        street = this.street,
        district = this.district,
        province = this.province,
        department = this.department,
        countryCode = this.countryCode,
        phone = this.phone
    )
}

fun Student.toCreateRequestDto(): CreateStudentRequestDto {
    return CreateStudentRequestDto(
        firstName = this.firstName,
        lastName = this.lastName,
        dni = this.dni,
        emailAddress = this.emailAddress,
        sex = this.sex,
        birthDate = this.birthDate,
        street = this.street,
        district = this.district,
        province = this.province,
        department = this.department,
        countryCode = this.countryCode,
        phone = this.phone
    )
}

fun Student.toUpdateRequestDto(): UpdateStudentRequestDto {
    return UpdateStudentRequestDto(
        firstName = this.firstName,
        lastName = this.lastName,
        dni = this.dni,
        sex = this.sex,
        birthDate = this.birthDate,
        street = this.street,
        district = this.district,
        province = this.province,
        department = this.department,
        countryCode = this.countryCode,
        phoneNumber = this.phone
    )
}
