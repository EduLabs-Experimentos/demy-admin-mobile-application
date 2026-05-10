package com.nistra.demy.admins.features.schedules.presentation.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.schedules.domain.models.DayOfWeek
import com.nistra.demy.admins.features.schedules.presentation.ui.components.ScheduleViewContainer
import com.nistra.demy.admins.features.schedules.presentation.ui.components.ScheduleViewerSelected
import com.nistra.demy.admins.features.schedules.presentation.ui.components.SchedulesViewerHeader
import com.nistra.demy.admins.features.schedules.presentation.viewmodel.ScheduleViewerViewModel


@Composable
fun ScheduleViewerScreen(
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewerViewModel = hiltViewModel()
) {
    val allSchedules by viewModel.allSchedules.collectAsState()
    val selectedSchedule by viewModel.selectedSchedule.collectAsState()

    val selectedScheduleName by viewModel.selectedScheduleName.collectAsState()

    val days = DayOfWeek.entries.map { it.name }
    val scrollState = rememberScrollState()

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        SchedulesViewerHeader(
            title = stringResource(com.nistra.demy.admins.R.string.schedules_viewer_screen_title),
            description = stringResource(R.string.schedules_viewer_screen_description)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ScheduleViewerSelected(
            allSchedules = allSchedules,
            selectedScheduleName = selectedScheduleName,
            onScheduleSelected = viewModel::selectSchedule
        )

        Spacer(modifier = Modifier.height(16.dp))

        ScheduleViewContainer(
            schedule = selectedSchedule,
            days = days,
            scrollState = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
        )
    }
}
