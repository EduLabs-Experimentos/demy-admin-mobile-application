package com.nistra.demy.admins.features.dashboard.presentation.navigation

sealed interface DashboardDestination {
    val route: String

    data object Overview : DashboardDestination {
        override val route = "dashboard/overview"
        fun toRoute() = route
    }
}
