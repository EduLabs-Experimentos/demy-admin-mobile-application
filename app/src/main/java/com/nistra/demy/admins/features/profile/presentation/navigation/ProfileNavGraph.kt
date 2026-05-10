package com.nistra.demy.admins.features.profile.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination
import com.nistra.demy.admins.features.profile.presentation.ui.screens.ProfileScreen

fun NavGraphBuilder.profileGraph(navController: NavHostController) {
    navigation(
        startDestination = ProfileDestination.Profile.route,
        route = MainDestination.Profile.route
    ) {
        composable(ProfileDestination.Profile.toRoute()) {
            ProfileScreen()
        }
    }
}


