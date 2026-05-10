package com.nistra.demy.admins.features.settings.presentation.navigation

sealed interface SettingsDestination {
    val route: String

    data object Settings : SettingsDestination {
        override val route = "settings"
        fun toRoute() = route
    }
}

