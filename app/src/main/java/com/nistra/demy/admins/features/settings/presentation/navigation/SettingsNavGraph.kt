package com.nistra.demy.admins.features.settings.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination
import com.nistra.demy.admins.features.settings.presentation.ui.screens.SettingsScreen

fun NavGraphBuilder.settingsGraph(navController: NavHostController) {
    navigation(
        startDestination = SettingsDestination.Settings.route,
        route = MainDestination.Settings.route
    ) {
        composable(SettingsDestination.Settings.toRoute()) {
            SettingsScreen()
        }
    }
}

