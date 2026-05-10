package com.nistra.demy.admins.features.teachers.presentation.state

import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.features.teachers.domain.model.Teacher

data class RegisterTeacherUiState(
    val isLoading: Boolean = false,
    val teachers: List<Teacher> = emptyList(),
    val filteredTeachers: List<Teacher> = emptyList(),
    val isLoadingTeachers: Boolean = false,
    val snackbarMessage: SnackbarMessage? = null
)

