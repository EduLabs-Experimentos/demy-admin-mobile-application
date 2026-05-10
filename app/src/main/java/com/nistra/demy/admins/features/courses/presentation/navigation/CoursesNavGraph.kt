package com.nistra.demy.admins.features.courses.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.courses.presentation.ui.screens.CoursesScreen
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination

fun NavGraphBuilder.coursesGraph(navController: NavHostController) {
    navigation(
        startDestination = CoursesDestination.Overview.route,
        route = MainDestination.Courses.route
    ) {
        composable(CoursesDestination.Overview.toRoute()) {
            CoursesScreen()
        }
    }
}
