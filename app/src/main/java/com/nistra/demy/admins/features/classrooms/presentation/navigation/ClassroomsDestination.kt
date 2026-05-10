package com.nistra.demy.admins.features.classrooms.presentation.navigation

sealed interface ClassroomsDestination {
    val route: String

    data object Overview : ClassroomsDestination {
        override val route = "classroom/overview"
        fun toRoute() = route
    }

}
