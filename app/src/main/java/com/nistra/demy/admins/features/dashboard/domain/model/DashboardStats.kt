package com.nistra.demy.admins.features.dashboard.domain.model

/**
 * Domain model representing dashboard statistics.
 *
 * This model contains all the key metrics displayed on the dashboard.
 * It's part of the domain layer and represents pure business data.
 *
 * @property academyName Name of the current academy.
 * @property totalIncome Total income amount.
 * @property totalExpense Total expenses amount.
 * @property balance Current financial balance (income - expense).
 * @property totalStudents Total number of students in current period.
 * @property totalTeachers Total number of teachers.
 * @property totalCourses Total number of courses.
 * @property totalClassrooms Total number of classrooms.
 * @property totalEnrollments Total number of enrollments.
 * @property totalSchedules Total number of active schedules.
 * @author Salim Ramirez
 */
data class DashboardStats(
    val academyName: String = "",
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0,
    val balance: Double = 0.0,
    val totalStudents: Int = 0,
    val totalTeachers: Int = 0,
    val totalCourses: Int = 0,
    val totalClassrooms: Int = 0,
    val totalEnrollments: Int = 0,
    val totalSchedules: Int = 0
)
