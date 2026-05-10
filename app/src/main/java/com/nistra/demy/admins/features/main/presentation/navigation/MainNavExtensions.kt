package com.nistra.demy.admins.features.main.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nistra.demy.admins.R

@Composable
fun NavHostController.currentTitle(): String {
    val parentRoute = currentParentRouteAsState()
    return when (parentRoute) {
        MainDestination.Dashboard.route -> stringResource(R.string.nav_title_dashboard)
        MainDestination.Teachers.route -> stringResource(R.string.nav_title_teachers)
        MainDestination.Students.route -> stringResource(R.string.nav_title_students)
        MainDestination.Billing.route -> stringResource(R.string.nav_title_billing)
        //MainDestination.Invoices.route -> stringResource(R.string.nav_title_invoices)
        MainDestination.Scheduling.route -> stringResource(R.string.drawer_destination_scheduling)
        MainDestination.Finance.route -> stringResource(R.string.nav_title_finance)
        MainDestination.Accounting.route -> stringResource(R.string.nav_title_accounting)
        MainDestination.Profile.route -> stringResource(R.string.nav_title_profile)
        MainDestination.Settings.route -> stringResource(R.string.nav_title_settings)
        MainDestination.Help.route -> stringResource(R.string.nav_title_help)
        else -> stringResource(R.string.nav_title_default)
    }
}

/**
 * Composable version that reactively observes navigation changes.
 *
 * This is the version that should be used in @Composable functions to ensure
 * it recomposes correctly when the route changes.
 *
 * Uses the same centralized mapping system as `currentParentRoute()`, so
 * adding a new module to `moduleToParentRouteMap` will automatically work
 * in both functions.
 *
 * @see currentParentRoute
 * @see moduleToParentRouteMap
 */
@Composable
fun NavHostController.currentParentRouteAsState(): String {
    val navBackStackEntry by currentBackStackEntryAsState()
    val childRoute = navBackStackEntry?.destination?.route
    val parentRoute = navBackStackEntry?.destination?.parent?.route

    // If a parent route is available in the graph, use it directly
    if (parentRoute != null) {
        return parentRoute
    }

    // If no parent route, extract the module from the child route
    if (childRoute != null) {
        val moduleName = childRoute.split("/").firstOrNull()

        // Search in the centralized map
        val mappedParentRoute = moduleName?.let { moduleToParentRouteMap[it] }

        if (mappedParentRoute != null) {
            return mappedParentRoute
        }
    }

    return childRoute ?: ""
}

/**
 * Centralized map of modules to their parent routes.
 *
 * This map defines the relationship between the module name (first segment of the route)
 * and its complete parent graph route.
 *
 * **IMPORTANT WHEN ADDING NEW MODULES:**
 * When you add a new module, just add one entry here:
 * ```
 * "courses" to MainDestination.Courses.route
 * ```
 *
 * This will automatically work for all destinations in the module:
 * - courses/list -> main/courses
 * - courses/create -> main/courses
 * - courses/edit/{id} -> main/courses
 *
 * @author Salim Ramirez
 */
private val moduleToParentRouteMap = mapOf(
    "dashboard" to MainDestination.Dashboard.route,
    "teachers" to MainDestination.Teachers.route,
    "students" to MainDestination.Students.route,
    "billing" to MainDestination.Billing.route,
    //"invoices" to MainDestination.Invoices.route,
    "finance" to MainDestination.Finance.route,
    "accounting" to MainDestination.Accounting.route,
    "profile" to MainDestination.Profile.route,
    "settings" to MainDestination.Settings.route,
    "help" to MainDestination.Help.route,
    "classrooms" to MainDestination.Classrooms.route,
    "schedules" to MainDestination.Schedules.route,
    "scheduling" to MainDestination.Scheduling.route,
    "courses" to MainDestination.Courses.route
    // Add new modules here
)

/**
 * Gets the current parent graph route.
 *
 * This function is essential for correct Drawer selection in a navigation
 * architecture with nested graphs.
 *
 * Example of navigation structure:
 * ```
 * main/teachers (parent graph)
 *   ├─ teachers/register (child destination)
 *   ├─ teachers/list (child destination)
 *   ├─ teachers/edit/{id} (child destination with parameters)
 *   └─ teachers/details/{id} (child destination with parameters)
 * ```
 *
 * When you're on any of these destinations, this function returns "main/teachers"
 * so the Drawer can correctly mark the "Teachers" option as selected.
 *
 * **SCALABILITY:**
 * This function is designed to work with multiple destinations within a module.
 * You only need to add the module once in `moduleToParentRouteMap` and it will work
 * for all its present and future destinations.
 *
 * @return The parent graph route, or the current route if not in a nested graph
 * @author Salim Ramirez
 */
fun NavHostController.currentParentRoute(): String {
    val entry = currentBackStackEntry ?: return ""
    val childRoute = entry.destination.route
    val parentRoute = entry.destination.parent?.route

    // If a parent route is available in the graph, use it directly
    if (parentRoute != null) {
        return parentRoute
    }

    // If no parent route, extract the module from the child route
    // Expected format: "{module}/{destination}[/{params}]"
    if (childRoute != null) {
        val moduleName = childRoute.split("/").firstOrNull()

        // Search in the centralized map
        val mappedParentRoute = moduleName?.let { moduleToParentRouteMap[it] }

        if (mappedParentRoute != null) {
            return mappedParentRoute
        }
    }

    return childRoute ?: ""
}

