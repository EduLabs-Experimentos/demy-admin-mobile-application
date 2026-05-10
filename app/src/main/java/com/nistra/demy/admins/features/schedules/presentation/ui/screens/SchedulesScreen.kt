package com.nistra.demy.admins.features.schedules.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.schedules.presentation.ui.components.RegisterSchedule
import com.nistra.demy.admins.features.schedules.presentation.ui.components.ScheduleList
import com.nistra.demy.admins.features.schedules.presentation.ui.components.SchedulesHeader
import com.nistra.demy.admins.features.schedules.presentation.viewmodel.SchedulesViewModel

@Composable
fun SchedulesScreen(
    viewModel: SchedulesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SchedulesHeader(
            title = stringResource(com.nistra.demy.admins.R.string.schedules_screen_title),
            description = stringResource(R.string.schedules_screen_description)
        )
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RegisterSchedule(
                modifier = Modifier.weight(0.5f),
                uiState = uiState,
                onScheduleNameChange = viewModel::onScheduleNameChange,
                onSaveScheduleName = viewModel::saveScheduleName,
                onClearForm = viewModel::clearSelectedSchedule,
                onSessionFormChange = viewModel::onSessionFormChange,
                onAddClassSession = viewModel::addClassSession,
                onDeleteClassSession = viewModel::deleteClassSession,
            )

            ScheduleList(
                modifier = Modifier.weight(0.5f),
                schedules = uiState.schedules,
                searchQuery = uiState.searchQuery,
                onScheduleSelected = viewModel::selectScheduleForEdit,
                onDeleteSchedule = viewModel::deleteSchedule,
                onSearchQueryChange = viewModel::searchSchedules,
                isLoading = uiState.isLoading
            )
        }
    }

}
