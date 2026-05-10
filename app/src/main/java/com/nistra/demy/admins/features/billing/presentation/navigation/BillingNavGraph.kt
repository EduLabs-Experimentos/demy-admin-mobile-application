package com.nistra.demy.admins.features.billing.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.billing.presentation.ui.screens.BillingAccountDetailsScreen
import com.nistra.demy.admins.features.billing.presentation.ui.screens.BillingScreen
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination

fun NavGraphBuilder.billingGraph(navController: NavHostController) {
    navigation(
        startDestination = BillingDestination.Billing.route,
        route = MainDestination.Billing.route
    ) {
        composable(BillingDestination.Billing.toRoute()) {
            BillingScreen(
                onGoToDetails = { accountId ->
                    navController.navigate(BillingDestination.BillingAccountDetails.createRoute(accountId))
                }
            )
        }

        composable(BillingDestination.BillingAccountDetails.toRoute()) {

            BillingAccountDetailsScreen(
                onNavigateBack = { navController.popBackStack()}
            )
        }
    }
}
