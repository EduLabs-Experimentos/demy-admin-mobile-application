package com.nistra.demy.admins.features.accounting.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.accounting.presentation.ui.screens.AccountingScreen
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination

fun NavGraphBuilder.accountingGraph(navController: NavHostController) {
    navigation(
        startDestination = AccountingDestination.Accounting.route,
        route = MainDestination.Accounting.route
    ) {
        composable(AccountingDestination.Accounting.toRoute()) {
            AccountingScreen()
        }
    }
}

