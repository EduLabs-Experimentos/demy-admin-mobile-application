package com.nistra.demy.admins.features.schedules.presentation.model

data class ClassSessionFormData(
    val selectedDay: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val courseId: Long? = null,
    val classroomId: Long? = null,
    val teacherId: String = "",
) {
    val isFormValid: Boolean
        get() = selectedDay.isNotBlank() &&
            startTime.isNotBlank() &&
            endTime.isNotBlank() &&
            courseId != null &&
            classroomId != null &&
            teacherId.isNotBlank()
}
