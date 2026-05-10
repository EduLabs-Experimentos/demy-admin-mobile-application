package com.nistra.demy.admins.features.courses.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.courses.presentation.ui.components.CourseList
import com.nistra.demy.admins.features.courses.presentation.ui.components.CoursesHeader
import com.nistra.demy.admins.features.courses.presentation.ui.components.RegisterCourse
import com.nistra.demy.admins.features.courses.presentation.viewmodel.CoursesViewModel


@Composable
fun CoursesScreen(
    viewModel: CoursesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val formData by viewModel.formData.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CoursesHeader(
            title = stringResource(com.nistra.demy.admins.R.string.courses_screen_title),
            description = stringResource(R.string.courses_screen_description)
        )
        Row (
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RegisterCourse(
                modifier = Modifier.weight(0.50f),
                courseToEdit = uiState.courseToEdit,
                formData = formData,
                isLoading = uiState.isLoading,
                errorMessage = uiState.errorMessage,
                onFormChange = viewModel::onCourseFormChange,
                onSaveCourseClick = viewModel::onSaveCourseClick,
                onClearFormClick = viewModel::onClearFormClick
            )

            CourseList(
                modifier = Modifier.weight(0.50f),
                courses = uiState.courses,
                onCourseSelected = viewModel::onCourseSelectedForEdit,
                onDeleteCourse = viewModel::onDeleteCourseClick,
                onSearchQueryChange = viewModel::searchCourses,
                searchQuery = uiState.searchQuery
            )
        }
    }
}
