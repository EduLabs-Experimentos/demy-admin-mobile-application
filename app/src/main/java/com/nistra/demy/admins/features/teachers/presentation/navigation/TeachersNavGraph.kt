package com.nistra.demy.admins.features.teachers.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination
import com.nistra.demy.admins.features.teachers.presentation.ui.screens.TeachersListScreen
import com.nistra.demy.admins.features.teachers.presentation.ui.screens.RegisterTeacherScreen

fun NavGraphBuilder.teachersGraph(navController: NavHostController) {
    navigation(
        startDestination = TeachersDestination.Register.route,
        route = MainDestination.Teachers.route
    ) {
        composable(TeachersDestination.Register.toRoute()) {
            RegisterTeacherScreen(
                onGoToList = { navController.navigate(TeachersDestination.List.toRoute()) }
            )
        }
        composable(TeachersDestination.List.toRoute()) {
            TeachersListScreen()
        }
    }
}
