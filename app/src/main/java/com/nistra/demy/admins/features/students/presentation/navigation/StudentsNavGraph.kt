package com.nistra.demy.admins.features.students.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination
import com.nistra.demy.admins.features.students.presentation.ui.screens.StudentsScreen

fun NavGraphBuilder.studentsGraph(navController: NavHostController) {
    navigation(
        startDestination = StudentsDestination.Overview.route,
        route = MainDestination.Students.route
    ) {
        composable(StudentsDestination.Overview.toRoute()) { StudentsScreen() }
    }
}


