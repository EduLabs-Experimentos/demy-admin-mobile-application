package com.nistra.demy.admins.features.main.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nistra.demy.admins.core.designsystem.layout.MainShell
import com.nistra.demy.admins.core.storage.SessionPreferences
import com.nistra.demy.admins.features.accounting.presentation.navigation.accountingGraph
import com.nistra.demy.admins.features.billing.presentation.navigation.billingGraph
import com.nistra.demy.admins.features.classrooms.presentation.navigation.classroomsGraph
import com.nistra.demy.admins.features.courses.presentation.navigation.coursesGraph
import com.nistra.demy.admins.features.dashboard.presentation.navigation.dashboardGraph
import com.nistra.demy.admins.features.enrollments.presentation.navigation.enrollmentsGraph
import com.nistra.demy.admins.features.finance.presentation.navigation.financeGraph
import com.nistra.demy.admins.features.help.presentation.navigation.helpGraph
//import com.nistra.demy.admins.features.invoicing.presentation.navigation.invoicesGraph
import com.nistra.demy.admins.features.main.presentation.viewmodel.MainViewModel
import com.nistra.demy.admins.features.periods.presentation.navigation.periodsGraph
import com.nistra.demy.admins.features.profile.presentation.navigation.profileGraph
import com.nistra.demy.admins.features.schedules.presentation.navigation.schedulesGraph
import com.nistra.demy.admins.features.settings.presentation.navigation.settingsGraph
import com.nistra.demy.admins.features.students.presentation.navigation.studentsGraph
import com.nistra.demy.admins.features.teachers.presentation.navigation.teachersGraph

@Composable
fun MainNavHost(
    rootNavController: NavHostController,
    sessionPreferences: SessionPreferences = hiltViewModel<MainViewModel>().sessionPreferences
) {
    val innerNavController = rememberNavController()

    MainShell(
        navController = innerNavController,
        rootNavController = rootNavController,
        title = innerNavController.currentTitle(),
        sessionPreferences = sessionPreferences
    ) {
        NavHost(
            navController = innerNavController,
            startDestination = MainDestination.Dashboard.route
        ) {
            dashboardGraph(innerNavController)
            teachersGraph(innerNavController)
            periodsGraph(innerNavController)
            studentsGraph(innerNavController)
            enrollmentsGraph(innerNavController)
            billingGraph(innerNavController)
            //invoicesGraph(innerNavController)
            financeGraph(innerNavController)
            accountingGraph(innerNavController)
            profileGraph(innerNavController)
            settingsGraph(innerNavController)
            helpGraph(innerNavController)
            classroomsGraph(innerNavController)
            schedulesGraph(innerNavController)
            coursesGraph(innerNavController)
        }
    }
}
