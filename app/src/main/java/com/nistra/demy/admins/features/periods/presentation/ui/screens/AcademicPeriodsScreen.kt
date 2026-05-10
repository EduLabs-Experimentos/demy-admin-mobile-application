package com.nistra.demy.admins.features.periods.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.periods.presentation.ui.components.AcademicPeriodList
import com.nistra.demy.admins.features.periods.presentation.ui.components.AcademicPeriodsHeader
import com.nistra.demy.admins.features.periods.presentation.ui.components.RegisterPeriod
import com.nistra.demy.admins.features.periods.presentation.viewmodel.AcademicPeriodsViewModel

@Composable
fun AcademicPeriodsScreen(
    viewModel: AcademicPeriodsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val formData by viewModel.formData.collectAsState()

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AcademicPeriodsHeader(
            title = stringResource(R.string.periods_screen_title),
            description = stringResource(R.string.periods_screen_description)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RegisterPeriod(
                modifier = Modifier.weight(0.50f),
                academicPeriodToEdit = uiState.academicPeriodToEdit,
                formData = formData,
                isLoading = uiState.isLoading,
                errorMessage = uiState.errorMessage,
                onFormChange = viewModel::onPeriodFormChange,
                onSavePeriodClick = viewModel::onSavePeriodClick,
                onClearFormClick = viewModel::onClearFormClick
            )

            AcademicPeriodList(
                modifier = Modifier.weight(0.50f),
                periods = uiState.academicPeriods,
                onPeriodSelected = viewModel::onPeriodSelectedForEdit,
                onDeletePeriod = viewModel::onDeletePeriodClick ,
                onSearchQueryChange = viewModel::searchPeriods,
                searchQuery = uiState.searchQuery
            )
        }
    }

}
