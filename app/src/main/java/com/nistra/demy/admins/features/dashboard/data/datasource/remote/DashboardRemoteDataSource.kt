package com.nistra.demy.admins.features.dashboard.data.datasource.remote

import com.nistra.demy.admins.features.dashboard.data.remote.dto.AcademyResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.ClassroomResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.CourseResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.EnrollmentResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.ScheduleResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.StudentResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.TeacherResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.TransactionResourceDto

/**
 * Remote data source interface for dashboard data.
 *
 * Defines the contract for fetching dashboard-related data from the API.
 *
 * @author Salim Ramirez
 */
interface DashboardRemoteDataSource {

    suspend fun fetchCurrentAcademy(): AcademyResourceDto

    suspend fun fetchAllTransactions(): List<TransactionResourceDto>

    suspend fun fetchAllTeachers(): List<TeacherResourceDto>

    suspend fun fetchAllStudents(): List<StudentResourceDto>

    suspend fun fetchAllEnrollments(): List<EnrollmentResourceDto>

    suspend fun fetchAllSchedules(): List<ScheduleResourceDto>

    suspend fun fetchAllCourses(): List<CourseResourceDto>

    suspend fun fetchAllClassrooms(): List<ClassroomResourceDto>
}

