package com.nistra.demy.admins.features.enrollments.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.enrollments.presentation.ui.screens.EnrollmentsScreen
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination

fun NavGraphBuilder.enrollmentsGraph(navController: NavHostController) {
    navigation(
        startDestination = EnrollmentsDestination.Overview.route,
        route = MainDestination.Enrollments.route
    ) {
        composable (EnrollmentsDestination.Overview.toRoute()) { EnrollmentsScreen() }
    }
}





