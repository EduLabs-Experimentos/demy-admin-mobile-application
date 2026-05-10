package com.nistra.demy.admins.features.dashboard.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.dashboard.presentation.ui.screens.DashboardScreen
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination

fun NavGraphBuilder.dashboardGraph(navController: NavHostController) {
    navigation(
        startDestination = DashboardDestination.Overview.route,
        route = MainDestination.Dashboard.route
    ) {
        composable(DashboardDestination.Overview.toRoute()) { DashboardScreen() }
    }
}
