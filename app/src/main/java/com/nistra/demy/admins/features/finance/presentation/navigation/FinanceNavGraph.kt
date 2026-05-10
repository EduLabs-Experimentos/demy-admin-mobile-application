package com.nistra.demy.admins.features.finance.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.finance.presentation.ui.screens.FinanceScreen
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination

fun NavGraphBuilder.financeGraph(navController: NavHostController) {
    navigation(
        startDestination = FinanceDestination.Finance.route,
        route = MainDestination.Finance.route
    ) {
        composable(FinanceDestination.Finance.toRoute()) {
            FinanceScreen()
        }
    }
}

