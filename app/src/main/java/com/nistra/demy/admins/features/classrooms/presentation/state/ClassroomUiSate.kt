package com.nistra.demy.admins.features.classrooms.presentation.state

import com.nistra.demy.admins.core.common.BaseUiState
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom

data class ClassroomUiState(
    val classrooms: List<Classroom> = emptyList(),
    val classroomToEdit: Classroom? = null,
    val searchQuery: String = "",
    val isFormSuccess: Boolean = false,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null
) : BaseUiState
