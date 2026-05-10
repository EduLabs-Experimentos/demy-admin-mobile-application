package com.nistra.demy.admins.features.dashboard.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nistra.demy.admins.core.designsystem.preview.MainLayoutPreviewContainer
import com.nistra.demy.admins.core.designsystem.preview.TabletPreviewLight
import com.nistra.demy.admins.core.designsystem.theme.DemyTheme
import com.nistra.demy.admins.features.dashboard.domain.model.DashboardStats
import com.nistra.demy.admins.features.dashboard.presentation.model.DashboardUiState
import com.nistra.demy.admins.features.dashboard.presentation.ui.components.DashboardAcademicRow
import com.nistra.demy.admins.features.dashboard.presentation.ui.components.DashboardExpenseCategoriesCard
import com.nistra.demy.admins.features.dashboard.presentation.ui.components.DashboardIncomeExpenseCard
import com.nistra.demy.admins.features.dashboard.presentation.ui.components.DashboardStatsRow
import com.nistra.demy.admins.features.dashboard.presentation.viewmodel.DashboardViewModel

/**
 * Main dashboard screen displaying key metrics and statistics.
 *
 * This screen follows Clean Architecture and MVVM pattern:
 * - Uses ViewModel to manage state and business logic
 * - Separates UI into reusable components
 * - Ready for integration with real data from repository
 *
 * @param modifier Modifier to be applied to the screen.
 * @param viewModel ViewModel that manages the dashboard state.
 * @author Salim Ramirez
 */
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    DashboardScreenContent(
        state = state,
        modifier = modifier
    )
}

/**
 * Dashboard screen content with state handling.
 */
@Composable
private fun DashboardScreenContent(
    state: DashboardUiState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is DashboardUiState.Loading -> {
            LoadingState(modifier = modifier)
        }
        is DashboardUiState.Error -> {
            ErrorState(
                message = state.message,
                modifier = modifier
            )
        }
        is DashboardUiState.Success -> {
            DashboardContent(
                state = state,
                modifier = modifier
            )
        }
    }
}

/**
 * Loading state UI.
 */
@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

/**
 * Error state UI.
 */
@Composable
private fun ErrorState(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
}

/**
 * Main dashboard content displaying all statistics and information.
 */
@Composable
private fun DashboardContent(
    state: DashboardUiState.Success,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top stats row - Academy, Income, Expense, Balance
        DashboardStatsRow(
            stats = state.stats,
            formatMoney = ::formatMoney
        )

        // Charts row - Income/Expense trend and Expense categories
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DashboardIncomeExpenseCard(
                data = state.chartData?.lineChartData,
                isLoading = false,
                modifier = Modifier.weight(1f)
            )

            DashboardExpenseCategoriesCard(
                data = state.chartData?.pieChartData,
                isLoading = false,
                modifier = Modifier.weight(1f)
            )
        }

        // Academic details row (keeping the last row)
        DashboardAcademicRow(
            totalTeachers = state.stats.totalTeachers,
            totalStudents = state.stats.totalStudents,
            totalEnrollments = state.stats.totalEnrollments,
            totalSchedules = state.stats.totalSchedules,
            totalCourses = state.stats.totalCourses,
            totalClassrooms = state.stats.totalClassrooms
        )
    }
}

/**
 * Formats a monetary amount to a string with currency symbol.
 *
 * @param amount The amount to format.
 * @return Formatted string with currency symbol.
 */
private fun formatMoney(amount: Double): String {
    return "S/ %,.2f".format(amount)
}

// ================================
// Preview
// ================================

@TabletPreviewLight
@Composable
private fun DashboardScreenPreview() {
    MainLayoutPreviewContainer(title = "Dashboard") {
        DemyTheme {
            DashboardScreenContent(
                state = DashboardUiState.Success(
                    stats = DashboardStats(
                        academyName = "Academia de Programación",
                        totalIncome = 25000.50,
                        totalExpense = 15000.00,
                        balance = 10000.50,
                        totalStudents = 200,
                        totalTeachers = 15,
                        totalCourses = 38,
                        totalClassrooms = 22,
                        totalEnrollments = 450,
                        totalSchedules = 25
                    )
                ),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

