package com.nistra.demy.admins.features.courses.presentation.state

import com.nistra.demy.admins.core.common.BaseUiState
import com.nistra.demy.admins.features.courses.domain.models.Course

data class CourseUiState(
    val courses: List<Course> = emptyList(),
    val courseToEdit: Course? = null,
    val searchQuery: String = "",
    val isFormSuccess: Boolean = false,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null
) : BaseUiState
