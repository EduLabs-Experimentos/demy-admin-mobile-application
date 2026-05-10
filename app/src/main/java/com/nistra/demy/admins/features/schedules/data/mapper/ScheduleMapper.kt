package com.nistra.demy.admins.features.schedules.data.mapper

import com.nistra.demy.admins.features.classrooms.data.remote.models.ClassroomResourceDto
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.courses.data.remote.models.CourseResourceDto
import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.schedules.data.remote.models.ClassSessionResourceDto
import com.nistra.demy.admins.features.schedules.data.remote.models.CreateScheduleRequestDto
import com.nistra.demy.admins.features.schedules.data.remote.models.ScheduleResourceDto
import com.nistra.demy.admins.features.schedules.domain.models.ClassSession
import com.nistra.demy.admins.features.schedules.domain.models.DayOfWeek
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.models.TimeRange
import com.nistra.demy.admins.features.teachers.data.remote.dto.TeacherResourceDto
import com.nistra.demy.admins.features.teachers.domain.model.Teacher

// Funciones auxiliares (sin cambios)
private fun timeStringToMinutes(timeStr: String): Int {
    return try {
        val parts = timeStr.split(":")
        val hours = parts.getOrElse(0) { "0" }.toInt()
        val minutes = parts.getOrElse(1) { "0" }.toInt()
        hours * 60 + minutes
    } catch (e: Exception) { 0 }
}

private fun calculateDurationMinutes(startTime: String, endTime: String): Int {
    val duration = timeStringToMinutes(endTime) - timeStringToMinutes(startTime)
    return if (duration < 0) 0 else duration
}

// --- MAPPERS PARA ENTIDADES ANIDADAS (Se asume su definición para ClassSession) ---

fun CourseResourceDto.toDomain(): Course? {
    return if (id != null && name != null && code != null) {
        Course(
            id = id,
            name = name,
            code = code,
            description = description.orEmpty()
        )
    } else null
}

fun ClassroomResourceDto.toDomain(): Classroom? {
    // Se asumen valores predeterminados para campos no nulos si son necesarios
    return if (id != null && code != null && campus != null) {
        Classroom(
            id = id,
            code = code,
            capacity = capacity ?: 0,
            campus = campus
        )
    } else null
}

fun TeacherResourceDto.toDomain(): Teacher? {
    // Nota: El ID del profesor es String.
    return if (id.isNullOrBlank().not() && firstName.isNullOrBlank().not() && lastName.isNullOrBlank().not()) {
        Teacher(
            id = id.orEmpty(),
            firstName = firstName.orEmpty(),
            lastName = lastName.orEmpty(),
            email = email.orEmpty(),
            countryCode = countryCode.orEmpty(),
            phone = phone.orEmpty()
        )
    } else null
}

// ---------------------------------------------------------------------------------

fun ClassSessionResourceDto.toDomain(): ClassSession? {
    val startTimeStr = startTime.orEmpty()
    val endTimeStr = endTime.orEmpty()
    val duration = calculateDurationMinutes(startTimeStr, endTimeStr)

    // 1. Mapear los objetos anidados
    val domainCourse = course?.toDomain()
    val domainClassroom = classroom?.toDomain()
    val domainTeacher = teacher?.toDomain()

    // 2. Validar que la información crítica esté presente
    if (id == null || dayOfWeek.isNullOrBlank() || domainCourse == null || domainClassroom == null || domainTeacher == null) {
        return null
    }

    // 3. Crear el modelo de dominio usando los objetos anidados
    return ClassSession(
        id = id,
        timeRange = TimeRange(startTimeStr, endTimeStr),
        dayOfWeek = try {
            DayOfWeek.valueOf(dayOfWeek.uppercase())
        } catch (e: IllegalArgumentException) {
            DayOfWeek.MONDAY
        },
        course = domainCourse,
        classroom = domainClassroom,
        teacher = domainTeacher,
        durationMinutes = duration
    )
}

fun ScheduleResourceDto.toDomain(): Schedule? {
    return if (id != null && name != null) {
        Schedule(
            id = id,
            name = name,
            classSessions = classSessions?.mapNotNull { it.toDomain() } ?: emptyList()
        )
    } else {
        null
    }
}

fun String.toCreateScheduleRequestDto(): CreateScheduleRequestDto {
    return CreateScheduleRequestDto(name = this)
}
