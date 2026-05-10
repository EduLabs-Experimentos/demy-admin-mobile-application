package com.nistra.demy.admins.features.help.presentation.navigation

sealed interface HelpDestination {
    val route: String

    data object Help : HelpDestination {
        override val route = "help"
        fun toRoute() = route
    }
}

