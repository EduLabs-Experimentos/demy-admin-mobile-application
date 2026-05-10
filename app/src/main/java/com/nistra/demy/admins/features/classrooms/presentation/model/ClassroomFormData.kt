package com.nistra.demy.admins.features.classrooms.presentation.model

data class ClassroomFormData(
    val code: String = "",
    val capacityText: String = "",
    val campus: String = ""
) {
    val capacity: Int = capacityText.toIntOrNull() ?: 0
}
