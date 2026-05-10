package com.nistra.demy.admins.features.help.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.help.presentation.ui.screens.HelpScreen
import com.nistra.demy.admins.features.help.presentation.ui.screens.PrivacyPolicyScreen
import com.nistra.demy.admins.features.help.presentation.ui.screens.TermsAndConditionsScreen
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination

fun NavGraphBuilder.helpGraph(navController: NavHostController) {
    navigation(
        startDestination = HelpDestination.Help.route,
        route = MainDestination.Help.route
    ) {
        composable(HelpDestination.Help.toRoute()) {
            HelpScreen(navController)
        }
        composable("terms_and_conditions") {
            TermsAndConditionsScreen(navController)
        }
        composable("privacy_policy") {
            PrivacyPolicyScreen(navController)
        }
    }
}
