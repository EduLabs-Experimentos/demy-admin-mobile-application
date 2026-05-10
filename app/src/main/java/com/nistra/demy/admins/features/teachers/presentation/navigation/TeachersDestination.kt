package com.nistra.demy.admins.features.teachers.presentation.navigation

sealed interface TeachersDestination {
    val route: String

    data object Register : TeachersDestination {
        override val route = "teachers/register"
        fun toRoute() = route
    }

    data object List : TeachersDestination {
        override val route = "teachers/list"
        fun toRoute() = route
    }
}
