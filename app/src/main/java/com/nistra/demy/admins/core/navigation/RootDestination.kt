package com.nistra.demy.admins.core.navigation

/**
 * Defines all navigation destinations used in the application.
 *
 * Each Destination represents a distinct screen or flow in the app.
 * This sealed interface provides a type-safe way to navigate without
 * relying on string literals.
 *
 * To add a new destination, simply create a new object that implements
 * the Destination interface and define its route.
 *
 * @param route The unique route string for the destination.
 * @see androidx.navigation.NavController
 * @see androidx.navigation.NavGraph
 * @see androidx.navigation.compose.NavHost
 * @author Salim Ramirez
 */
sealed interface RootDestination {
    val route: String

    data object Splash : RootDestination {
        override val route = "splash"
        fun toRoute() = route
    }

    data object AuthGraph : RootDestination {
        override val route = "auth-graph"
        fun toRoute() = route
    }

    data object MainGraph : RootDestination {
        override val route = "main-graph"
        fun toRoute() = route
    }

    data object Courses : RootDestination {
        override val route = "courses"
        fun toRoute() = route
    }


}
