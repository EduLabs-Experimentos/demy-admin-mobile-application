package com.nistra.demy.admins.features.profile.presentation.navigation

sealed interface ProfileDestination {
    val route: String

    data object Profile : ProfileDestination {
        override val route = "profile"
        fun toRoute() = route
    }
}

