package com.nistra.demy.admins.core.navigation.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination

/**
 * Main destinations for the app's top-level navigation.
 *
 * @param id The unique identifier for the destination.
 * @param labelResId The string resource ID for the display label.
 * @param icon The icon representing the destination.
 * @author Salim Ramirez
 */
sealed class DrawerDestination(val id: String, @param:StringRes val labelResId: Int, val icon: ImageVector) {
    data object Dashboard : DrawerDestination(MainDestination.Dashboard.route, R.string.drawer_destination_dashboard, Icons.Filled.Dashboard)
    data object Teachers : DrawerDestination(MainDestination.Teachers.route, R.string.drawer_destination_teachers, Icons.Filled.Work)
    data object Students : DrawerDestination(MainDestination.Students.route, R.string.drawer_destination_students, Icons.Filled.People)
    data object Classrooms : DrawerDestination(MainDestination.Classrooms.route, R.string.drawer_destination_classrooms, Icons.Filled.School)
    data object Courses : DrawerDestination(MainDestination.Courses.route, R.string.drawer_destination_courses, Icons.AutoMirrored.Filled.MenuBook)
    data object Schedules : DrawerDestination(MainDestination.Schedules.route, R.string.drawer_destination_schedules, Icons.Filled.Schedule)
    data object Periods : DrawerDestination(MainDestination.AcademicPeriods.route, R.string.drawer_destination_periods, Icons.Filled.CalendarMonth)
    data object Enrollments : DrawerDestination(MainDestination.Enrollments.route, R.string.drawer_destination_enrollments, Icons.Filled.AppRegistration)
    data object Scheduling : DrawerDestination(MainDestination.Scheduling.route, R.string.drawer_destination_scheduling, Icons.Filled.CalendarToday)
    data object Billing : DrawerDestination(MainDestination.Billing.route, R.string.drawer_destination_billing, Icons.Filled.Payment)
   // data object Invoices : DrawerDestination(MainDestination.Invoices.route, R.string.drawer_destination_invoices, Icons.Filled.Description)
    data object Finance : DrawerDestination(MainDestination.Finance.route, R.string.drawer_destination_finance, Icons.Filled.AccountBalance)
    data object Accounting : DrawerDestination(MainDestination.Accounting.route, R.string.drawer_destination_accounting, Icons.Filled.AccountBalanceWallet)
    data object Settings : DrawerDestination(MainDestination.Settings.route, R.string.drawer_destination_settings, Icons.Filled.Settings)
    data object Help : DrawerDestination(MainDestination.Help.route, R.string.drawer_destination_help, Icons.AutoMirrored.Filled.Help)
    data object LogOut : DrawerDestination("logout", R.string.drawer_destination_logout, Icons.AutoMirrored.Filled.ExitToApp)
}
