package com.nistra.demy.admins.features.periods.presentation.state

import androidx.room.Query
import com.nistra.demy.admins.core.common.BaseUiState
import com.nistra.demy.admins.features.periods.domain.model.AcademicPeriod

data class AcademicPeriodUiState (
    val academicPeriods: List<AcademicPeriod> = emptyList(),
    val academicPeriodToEdit: AcademicPeriod? = null,
    val searchQuery: String = "",
    val isFormSuccess: Boolean = false,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null
): BaseUiState
