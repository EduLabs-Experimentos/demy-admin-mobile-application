package com.nistra.demy.admins.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

/**
 * Main navigation entry point for the Demy Admins application.
 *
 * This composable function initializes the navigation controller and sets up
 * the root navigation graph. It serves as the top-level navigation component
 * that orchestrates navigation between different features (Splash, Auth, Main).
 *
 * The navigator manages the entire navigation flow of the application:
 * - Splash screen (initial screen)
 * - Authentication flow (login/register)
 * - Main application flow (dashboard and other features)
 *
 * @author Salim Ramirez
 */
@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    RootNavGraph(navController = navController)
}
