package com.nistra.demy.admins.features.students.presentation.state

import com.nistra.demy.admins.core.common.BaseUiState
import com.nistra.demy.admins.features.students.domain.model.Student


data class StudentUiState(
    val students: List<Student> = emptyList(),
    val studentToEdit: Student? = null,
    val searchQuery: String = "",
    val isFormSuccess: Boolean = false,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null
) : BaseUiState
