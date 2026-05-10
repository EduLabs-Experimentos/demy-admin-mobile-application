package com.nistra.demy.admins.features.schedules.presentation.model

data class ScheduleFormData(
    val name: String = "",
) {
    val isFormValid: Boolean
        get() = name.isNotBlank()
}
