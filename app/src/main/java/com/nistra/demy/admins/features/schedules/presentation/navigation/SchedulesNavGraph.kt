package com.nistra.demy.admins.features.schedules.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination
import com.nistra.demy.admins.features.schedules.presentation.ui.screens.ScheduleViewerScreen
import com.nistra.demy.admins.features.schedules.presentation.ui.screens.SchedulesScreen

fun NavGraphBuilder.schedulesGraph(navController: NavHostController) {
    navigation(
        startDestination = SchedulesDestination.Overview.route,
        route = MainDestination.Schedules.route // main/schedules
    ) {
        composable(SchedulesDestination.Overview.route) {
            SchedulesScreen()
        }
    }



    navigation(
        startDestination = SchedulesDestination.ScheduleViewer.route,
        route = MainDestination.Scheduling.route // main/scheduling
    ) {
        // Usamos la pantalla del visor como punto de entrada para esta sección.
        composable(SchedulesDestination.ScheduleViewer.route) {
            ScheduleViewerScreen()
        }
    }
}
