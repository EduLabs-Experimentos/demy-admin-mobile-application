package com.nistra.demy.admins.features.main.presentation.navigation

sealed interface MainDestination {
    val route: String

    data object Dashboard : MainDestination {
        override val route = "main/dashboard"
        fun toRoute() = route
    }

    data object Teachers : MainDestination {
        override val route = "main/teachers"
        fun toRoute() = route
    }

    data object Students : MainDestination {
        override val route = "main/students"
        fun toRoute() = route
    }

    data object AcademicPeriods : MainDestination {
        override val route = "main/academic-periods"
        fun toRoute() = route
    }

    data object Billing : MainDestination {
        override val route = "main/billing"
        fun toRoute() = route
    }

    /* data object Invoices : MainDestination {
         override val route = "main/invoices"
         fun toRoute() = route
     } */

    data object Finance : MainDestination {
        override val route = "main/finance"
        fun toRoute() = route
    }

    data object Accounting : MainDestination {
        override val route = "main/accounting"
        fun toRoute() = route
    }

    data object Profile : MainDestination {
        override val route = "main/profile"
        fun toRoute() = route
    }

    data object Enrollments : MainDestination {
        override val route = "main/enrollments"
        fun toRoute() = route
    }

    data object Settings : MainDestination {
        override val route = "main/settings"
        fun toRoute() = route
    }

    data object Help : MainDestination {
        override val route = "main/help"
        fun toRoute() = route
    }

    data object Classrooms: MainDestination {
        override val route = "main/classrooms"
        fun toRoute() = route
    }

    data object Schedules : MainDestination {
        override val route = "main/schedules"
        fun toRoute() = route
    }

    data object Scheduling : MainDestination {
        override val route = "main/scheduling"
        fun toRoute() = route
    }

    data object Courses : MainDestination {
        override val route = "main/courses"
        fun toRoute() = route
    }

}
