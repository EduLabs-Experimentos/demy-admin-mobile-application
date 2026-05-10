package com.nistra.demy.admins.features.dashboard.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.dashboard.domain.usecase.GetDashboardStatsUseCase
import com.nistra.demy.admins.features.dashboard.presentation.model.DashboardChartProcessor
import com.nistra.demy.admins.features.dashboard.presentation.model.DashboardUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Dashboard screen.
 *
 * Manages the UI state and coordinates data loading from the use case.
 * Follows MVVM pattern with Clean Architecture.
 *
 * @property getDashboardStatsUseCase Use case for fetching dashboard statistics.
 * @author Salim Ramirez
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDashboardStatsUseCase: GetDashboardStatsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboardStats()
    }

    /**
     * Loads dashboard statistics from the use case.
     */
    fun loadDashboardStats() = viewModelScope.launch {
        _uiState.value = DashboardUiState.Loading

        getDashboardStatsUseCase()
            .onSuccess { (stats, transactions) ->
                val chartData = if (transactions.isNotEmpty()) {
                    DashboardChartProcessor.processTransactions(transactions)
                } else {
                    null
                }

                _uiState.value = DashboardUiState.Success(
                    stats = stats,
                    chartData = chartData
                )
            }
            .onFailure { error ->
                _uiState.value = DashboardUiState.Error(
                    message = error.message ?: "Error loading dashboard"
                )
            }
    }
}
