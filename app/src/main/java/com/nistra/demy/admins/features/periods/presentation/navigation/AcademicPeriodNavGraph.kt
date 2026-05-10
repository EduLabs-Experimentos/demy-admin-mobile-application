package com.nistra.demy.admins.features.periods.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination
import com.nistra.demy.admins.features.periods.presentation.ui.screens.AcademicPeriodsScreen

fun NavGraphBuilder.periodsGraph(navController: NavHostController) {
    navigation(
        startDestination = AcademicPeriodsDestination.Overview.route,
        route = MainDestination.AcademicPeriods.route
    ) {
        composable(AcademicPeriodsDestination.Overview.toRoute()) {
            AcademicPeriodsScreen()
        }
    }
}
