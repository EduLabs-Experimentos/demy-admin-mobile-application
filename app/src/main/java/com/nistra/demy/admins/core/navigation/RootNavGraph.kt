package com.nistra.demy.admins.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nistra.demy.admins.features.auth.presentation.navigation.AuthNavHost
import com.nistra.demy.admins.features.main.presentation.navigation.MainNavHost
import com.nistra.demy.admins.features.splash.presentation.ui.screens.SplashScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String = RootDestination.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(RootDestination.Splash.route) {
            SplashScreen(
                onSessionActive = {
                    navController.navigate(RootDestination.MainGraph.toRoute()) {
                        popUpTo(RootDestination.Splash.route) { inclusive = true }
                    }
                },
                onSessionInactive = {
                    navController.navigate(RootDestination.AuthGraph.toRoute()) {
                        popUpTo(RootDestination.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(RootDestination.AuthGraph.route) {
            AuthNavHost(
                onLoggedIn = {
                navController.navigate(RootDestination.MainGraph.toRoute()) {
                    popUpTo(RootDestination.AuthGraph.route) { inclusive = true }
                }
            })
        }

        composable(RootDestination.MainGraph.route) {
            MainNavHost(rootNavController = navController)
        }
    }
}
