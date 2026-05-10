package com.nistra.demy.admins.features.enrollments.presentation.navigation

sealed interface EnrollmentsDestination {
    val route: String

    data object Overview: EnrollmentsDestination {
        override val route = "enrollments/overview"
        fun toRoute() = route
    }
}
