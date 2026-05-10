package com.nistra.demy.admins.features.teachers.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.feedback.DemySnackbarHost
import com.nistra.demy.admins.core.designsystem.components.feedback.SnackbarEffect
import com.nistra.demy.admins.core.designsystem.components.feedback.rememberDemySnackbarState
import com.nistra.demy.admins.features.teachers.presentation.ui.components.TeachersHeader
import com.nistra.demy.admins.features.teachers.presentation.ui.components.TeacherRegistrationForm
import com.nistra.demy.admins.features.teachers.presentation.ui.components.TeacherSearchPanel
import com.nistra.demy.admins.features.teachers.presentation.viewmodel.RegisterTeacherViewModel

/**
 * Screen for registering new teachers and searching existing ones.
 *
 * Displays a form for teacher registration on the left side and a searchable
 * list of existing teachers on the right side.
 *
 * @param viewModel The ViewModel managing the screen state and business logic.
 * @param onGoToList Callback to navigate to the teachers list screen.
 * @author Salim Ramirez
 */
@Composable
fun RegisterTeacherScreen(
    viewModel: RegisterTeacherViewModel = hiltViewModel(),
    @Suppress("UNUSED_PARAMETER") onGoToList: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val formData by viewModel.formData.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val snackbarState = rememberDemySnackbarState()

    SnackbarEffect(
        message = uiState.snackbarMessage,
        snackbarState = snackbarState,
        onMessageShown = viewModel::clearSnackbarMessage
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TeachersHeader(
                title = stringResource(R.string.teachers_screen_title),
                description = stringResource(R.string.teachers_screen_description)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TeacherRegistrationForm(
                    formData = formData,
                    onFormChange = viewModel::onFieldChange,
                    onSubmit = viewModel::registerTeacher,
                    modifier = Modifier.weight(1f),
                    isLoading = uiState.isLoading
                )

                TeacherSearchPanel(
                    searchQuery = searchQuery,
                    onSearchQueryChange = viewModel::onSearchQueryChange,
                    teachers = uiState.filteredTeachers,
                    modifier = Modifier.weight(1f),
                    isLoading = uiState.isLoadingTeachers
                )
            }
        }

        DemySnackbarHost(
            snackbarState = snackbarState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true, widthDp = 1200, heightDp = 800)
@Composable
fun RegisterTeacherScreenPreview() {
    MaterialTheme {
        RegisterTeacherScreen()
    }
}
