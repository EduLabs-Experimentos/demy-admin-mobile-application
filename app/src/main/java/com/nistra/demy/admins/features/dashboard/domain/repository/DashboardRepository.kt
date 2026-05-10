package com.nistra.demy.admins.features.dashboard.domain.repository

import com.nistra.demy.admins.features.dashboard.data.remote.dto.TransactionResourceDto
import com.nistra.demy.admins.features.dashboard.domain.model.DashboardStats

interface DashboardRepository {
    suspend fun fetchStats(): DashboardStats
    suspend fun fetchStatsAndTransactions(): Pair<DashboardStats, List<TransactionResourceDto>>
}
