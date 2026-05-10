package com.nistra.demy.admins.features.students.presentation.navigation

sealed interface StudentsDestination {
    val route: String

    data object Overview : StudentsDestination {
        override val route = "students/overview"
        fun toRoute() = route
    }
}

