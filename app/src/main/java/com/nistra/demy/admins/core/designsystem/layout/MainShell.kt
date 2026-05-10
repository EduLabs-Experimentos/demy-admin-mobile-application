package com.nistra.demy.admins.core.designsystem.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.nistra.demy.admins.core.designsystem.config.DrawerConfig
import com.nistra.demy.admins.core.designsystem.model.UserUi
import com.nistra.demy.admins.core.navigation.RootDestination
import com.nistra.demy.admins.core.navigation.model.DrawerDestination
import com.nistra.demy.admins.core.navigation.navigateOnce
import com.nistra.demy.admins.core.storage.SessionPreferences
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination
import com.nistra.demy.admins.features.main.presentation.navigation.currentParentRouteAsState
import kotlinx.coroutines.launch

/**
 * Main shell composable that wraps the main layout with navigation logic.
 *
 * This component handles:
 * - Navigation between main sections
 * - Logout functionality
 * - Current route tracking
 * - Session management
 *
 * It separates navigation logic from the pure UI layout.
 *
 * @param navController Navigation controller for the main graph.
 * @param rootNavController Navigation controller for the root graph.
 * @param title Current screen title.
 * @param sessionPreferences Session preferences for logout.
 * @param content Main content composable.
 * @author Salim Ramirez
 */
@Composable
fun MainShell(
    navController: NavHostController,
    rootNavController: NavHostController,
    title: String,
    sessionPreferences: SessionPreferences,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val currentRoute = navController.currentParentRouteAsState()
    val isProfileSelected = currentRoute == MainDestination.Profile.route

    MainLayout(
        title = title,
        appName = "Demy Admins",
        user = UserUi(
            name = "Profile",
            role = "Administrator"
        ),
        onUserClick = {
            navController.navigateOnce(MainDestination.Profile.route)
        },
        isProfileSelected = isProfileSelected,
        drawerSections = DrawerConfig.getSections(),
        selectedDestinationId = currentRoute,
        onDestinationClick = { destination ->
            handleDestinationClick(
                destination = destination,
                navController = navController,
                rootNavController = rootNavController,
                sessionPreferences = sessionPreferences,
                scope = scope
            )
        }
    ) {
        content()
    }
}

/**
 * Handles clicks on drawer destinations.
 *
 * @param destination The clicked destination.
 * @param navController Navigation controller for the main graph.
 * @param rootNavController Navigation controller for the root graph.
 * @param sessionPreferences Session preferences for logout.
 * @param scope Coroutine scope for async operations.
 */
private fun handleDestinationClick(
    destination: DrawerDestination,
    navController: NavHostController,
    rootNavController: NavHostController,
    sessionPreferences: SessionPreferences,
    scope: kotlinx.coroutines.CoroutineScope
) {
    when {
        destination == DrawerDestination.LogOut -> {
            handleLogout(rootNavController, sessionPreferences, scope)
        }
        destination.id in DrawerConfig.implementedRoutes -> {
            navController.navigateOnce(destination.id)
        }
        else -> {
            logUnimplementedNavigation(destination)
        }
    }
}

/**
 * Handles logout functionality.
 */
private fun handleLogout(
    rootNavController: NavHostController,
    sessionPreferences: SessionPreferences,
    scope: kotlinx.coroutines.CoroutineScope
) {
    scope.launch {
        sessionPreferences.clearSession()
        rootNavController.navigate(RootDestination.AuthGraph.toRoute()) {
            popUpTo(RootDestination.MainGraph.toRoute()) { inclusive = true }
        }
    }
}

/**
 * Logs a warning for unimplemented navigation.
 */
private fun logUnimplementedNavigation(destination: DrawerDestination) {
    android.util.Log.w(
        "MainShell",
        "Navigation to ${destination.id} not yet implemented"
    )
}
