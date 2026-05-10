package com.nistra.demy.admins.features.courses.presentation.navigation

sealed interface CoursesDestination {
    val route: String

    data object Overview : CoursesDestination {
        override val route = "courses/overview"
        fun toRoute() = route
    }

}
