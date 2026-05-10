package com.nistra.demy.admins.features.dashboard.data.repository

import com.nistra.demy.admins.features.dashboard.data.datasource.remote.DashboardRemoteDataSource
import com.nistra.demy.admins.features.dashboard.data.mapper.toDashboardStats
import com.nistra.demy.admins.features.dashboard.data.remote.dto.TransactionResourceDto
import com.nistra.demy.admins.features.dashboard.domain.model.DashboardStats
import com.nistra.demy.admins.features.dashboard.domain.repository.DashboardRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

/**
 * Implementation of the dashboard repository.
 *
 * Fetches data from the remote data source and maps it to domain models.
 * Uses coroutines to fetch all data concurrently for better performance.
 *
 * @property remoteDataSource The remote data source for dashboard data.
 * @author Salim Ramirez
 */
class DashboardRepositoryImpl @Inject constructor(
    private val remoteDataSource: DashboardRemoteDataSource
) : DashboardRepository {

    override suspend fun fetchStats(): DashboardStats = coroutineScope {
        // Fetch all data concurrently
        val academyDeferred = async { remoteDataSource.fetchCurrentAcademy() }
        val transactionsDeferred = async { remoteDataSource.fetchAllTransactions() }
        val studentsDeferred = async { remoteDataSource.fetchAllStudents() }
        val teachersDeferred = async { remoteDataSource.fetchAllTeachers() }
        val coursesDeferred = async { remoteDataSource.fetchAllCourses() }
        val classroomsDeferred = async { remoteDataSource.fetchAllClassrooms() }
        val enrollmentsDeferred = async { remoteDataSource.fetchAllEnrollments() }
        val schedulesDeferred = async { remoteDataSource.fetchAllSchedules() }

        // Wait for all results
        val academy = academyDeferred.await()
        val transactions = transactionsDeferred.await()
        val students = studentsDeferred.await()
        val teachers = teachersDeferred.await()
        val courses = coursesDeferred.await()
        val classrooms = classroomsDeferred.await()
        val enrollments = enrollmentsDeferred.await()
        val schedules = schedulesDeferred.await()

        // Map to domain model
        toDashboardStats(
            academy = academy,
            transactions = transactions,
            studentsCount = students.size,
            teachersCount = teachers.size,
            coursesCount = courses.size,
            classroomsCount = classrooms.size,
            enrollmentsCount = enrollments.size,
            schedulesCount = schedules.size
        )
    }

    override suspend fun fetchStatsAndTransactions(): Pair<DashboardStats, List<TransactionResourceDto>> = coroutineScope {
        // Fetch all data concurrently
        val academyDeferred = async { remoteDataSource.fetchCurrentAcademy() }
        val transactionsDeferred = async { remoteDataSource.fetchAllTransactions() }
        val studentsDeferred = async { remoteDataSource.fetchAllStudents() }
        val teachersDeferred = async { remoteDataSource.fetchAllTeachers() }
        val coursesDeferred = async { remoteDataSource.fetchAllCourses() }
        val classroomsDeferred = async { remoteDataSource.fetchAllClassrooms() }
        val enrollmentsDeferred = async { remoteDataSource.fetchAllEnrollments() }
        val schedulesDeferred = async { remoteDataSource.fetchAllSchedules() }

        // Wait for all results
        val academy = academyDeferred.await()
        val transactions = transactionsDeferred.await()
        val students = studentsDeferred.await()
        val teachers = teachersDeferred.await()
        val courses = coursesDeferred.await()
        val classrooms = classroomsDeferred.await()
        val enrollments = enrollmentsDeferred.await()
        val schedules = schedulesDeferred.await()

        // Map to domain model
        val stats = toDashboardStats(
            academy = academy,
            transactions = transactions,
            studentsCount = students.size,
            teachersCount = teachers.size,
            coursesCount = courses.size,
            classroomsCount = classrooms.size,
            enrollmentsCount = enrollments.size,
            schedulesCount = schedules.size
        )

        Pair(stats, transactions)
    }
}
