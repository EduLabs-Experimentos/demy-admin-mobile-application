package com.nistra.demy.admins.core.navigation

import androidx.navigation.NavController

/**
 * Navigates to a route only if you're not already on it.
 *
 * This function prevents duplicate navigations that cause flickering and UI issues.
 * It compares both the current route and the parent graph route to correctly handle
 * nested navigation graphs.
 *
 * Example of nested graph:
 * - Parent graph: "main/teachers"
 * - Child route: "teachers/register"
 *
 * If you're on "teachers/register" and navigate to "main/teachers", the function
 * will detect that you're already in that graph and block the navigation.
 *
 * @param route The destination route to navigate to
 * @param popUpToRoute Optional route to pop up to in the back stack
 * @param inclusive If true, includes the popUpToRoute in the pop operation
 *
 * @author Salim Ramirez
 */
fun NavController.navigateOnce(
    route: String,
    popUpToRoute: String? = null,
    inclusive: Boolean = false
) {
    val currentEntry = currentBackStackEntry
    val currentRoute = currentEntry?.destination?.route
    val currentParentRoute = currentEntry?.destination?.parent?.route

    // Compare both current route and parent graph route
    // to avoid duplicate navigations in nested graphs
    if (currentRoute == route || currentParentRoute == route) {
        return
    }

    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
        if (popUpToRoute != null) {
            popUpTo(popUpToRoute) {
                saveState = true
                this.inclusive = inclusive
            }
        }
    }
}

fun NavController.navigateOnce(destination: RootDestination) {
    navigateOnce(destination.route)
}

fun NavController.popToRoot() {
    this.popBackStack(this.graph.startDestinationId, false)
}

fun NavController.back() {
    popBackStack()
}

fun NavController.replaceCurrent(route: String) {
    popBackStack()
    navigateOnce(route)
}
