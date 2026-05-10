package com.nistra.demy.admins.features.schedules.presentation.navigation

sealed interface SchedulesDestination {
    val route: String

    /**
     * The main entry point to the Schedules feature, showing the list/overview.
     * Route: schedules/overview
     */
    data object Overview : SchedulesDestination {
        override val route = "schedules/overview"
        fun toRoute() = route
    }

    /**
     * Detailed view for a specific schedule.
     * Route: schedules/viewer/{scheduleId}
     */
    data object ScheduleViewer : SchedulesDestination {
        override val route = "schedules/viewer/{scheduleId}"
        fun toRoute(scheduleId: String) = "schedules/viewer/$scheduleId"
        fun toRoute() = route
    }
}
