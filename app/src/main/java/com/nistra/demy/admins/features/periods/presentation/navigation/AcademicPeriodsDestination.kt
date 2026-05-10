package com.nistra.demy.admins.features.periods.presentation.navigation

sealed interface AcademicPeriodsDestination {
    val route: String

    data object Overview: AcademicPeriodsDestination {
        override val route = "academic-periods/overview"
        fun toRoute() = route
    }
}
