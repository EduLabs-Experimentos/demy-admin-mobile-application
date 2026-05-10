package com.nistra.demy.admins.features.dashboard.data.mapper

import com.nistra.demy.admins.features.dashboard.data.remote.dto.AcademyResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.TransactionResourceDto
import com.nistra.demy.admins.features.dashboard.domain.model.DashboardStats

/**
 * Calculates total income and expense from a list of transactions.
 *
 * @param transactions List of transaction DTOs.
 * @return Pair of (totalIncome, totalExpense).
 */
fun calculateFinancials(transactions: List<TransactionResourceDto>): Pair<Double, Double> {
    var totalIncome = 0.0
    var totalExpense = 0.0

    transactions.forEach { transaction ->
        val amount = transaction.amount.toDoubleOrNull() ?: 0.0
        when (transaction.type.uppercase()) {
            "INCOME" -> totalIncome += amount
            "EXPENSE" -> totalExpense += amount
        }
    }

    return Pair(totalIncome, totalExpense)
}

/**
 * Maps academy and transaction data to DashboardStats domain model.
 *
 * @param academy Academy information DTO.
 * @param transactions List of transaction DTOs.
 * @param studentsCount Total number of students.
 * @param teachersCount Total number of teachers.
 * @param coursesCount Total number of courses.
 * @param classroomsCount Total number of classrooms.
 * @param enrollmentsCount Total number of enrollments.
 * @param schedulesCount Total number of schedules.
 * @return DashboardStats domain model.
 */
fun toDashboardStats(
    academy: AcademyResourceDto,
    transactions: List<TransactionResourceDto>,
    studentsCount: Int,
    teachersCount: Int,
    coursesCount: Int,
    classroomsCount: Int,
    enrollmentsCount: Int,
    schedulesCount: Int
): DashboardStats {
    val (totalIncome, totalExpense) = calculateFinancials(transactions)
    val balance = totalIncome - totalExpense

    return DashboardStats(
        academyName = academy.academyName,
        totalIncome = totalIncome,
        totalExpense = totalExpense,
        balance = balance,
        totalStudents = studentsCount,
        totalTeachers = teachersCount,
        totalCourses = coursesCount,
        totalClassrooms = classroomsCount,
        totalEnrollments = enrollmentsCount,
        totalSchedules = schedulesCount
    )
}

