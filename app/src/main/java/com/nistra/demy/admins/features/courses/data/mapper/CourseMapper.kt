package com.nistra.demy.admins.features.courses.data.mapper

import com.nistra.demy.admins.features.courses.data.remote.models.CourseResourceDto
import com.nistra.demy.admins.features.courses.data.remote.models.CreateCourseRequestDto
import com.nistra.demy.admins.features.courses.data.remote.models.UpdateCourseRequestDto
import com.nistra.demy.admins.features.courses.domain.models.Course

fun CourseResourceDto.toDomain(): Course {
    return Course(
        id = this.id,
        name = this.name,
        code = this.code,
        description = this.description
    )
}

fun Course.toCreateRequestDto(): CreateCourseRequestDto {
    return CreateCourseRequestDto(
        name = this.name,
        code = this.code,
        description = this.description
    )
}

fun Course.toUpdateRequestDto(): UpdateCourseRequestDto {
    return UpdateCourseRequestDto(
        name = this.name,
        code = this.code,
        description = this.description
    )
}
