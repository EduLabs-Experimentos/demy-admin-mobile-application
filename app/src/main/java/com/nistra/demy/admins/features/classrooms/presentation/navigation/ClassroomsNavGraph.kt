package com.nistra.demy.admins.features.classrooms.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.classrooms.presentation.ui.screens.ClassroomsScreen
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination

fun NavGraphBuilder.classroomsGraph(navController: NavHostController) {
    navigation(
        startDestination = ClassroomsDestination.Overview.route,
        route = MainDestination.Classrooms.route
    ) {
        composable(ClassroomsDestination.Overview.toRoute()) {
            ClassroomsScreen()
        }
    }
}
