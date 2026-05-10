package com.nistra.demy.admins.features.dashboard.domain.usecase

import com.nistra.demy.admins.features.dashboard.data.remote.dto.TransactionResourceDto
import com.nistra.demy.admins.features.dashboard.domain.model.DashboardStats
import com.nistra.demy.admins.features.dashboard.domain.repository.DashboardRepository
import javax.inject.Inject

/**
 * Use case for fetching dashboard statistics.
 *
 * This use case encapsulates the business logic for retrieving
 * all dashboard statistics from the repository.
 *
 * @property repository The dashboard repository.
 * @author Salim Ramirez
 */
class GetDashboardStatsUseCase @Inject constructor(
    private val repository: DashboardRepository
) {
    suspend operator fun invoke(): Result<Pair<DashboardStats, List<TransactionResourceDto>>> {
        return runCatching { repository.fetchStatsAndTransactions() }
    }
}

