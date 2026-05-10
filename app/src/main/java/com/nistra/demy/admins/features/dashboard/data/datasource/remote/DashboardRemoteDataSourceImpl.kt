package com.nistra.demy.admins.features.dashboard.data.datasource.remote

import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.dashboard.data.remote.api.DashboardApi
import com.nistra.demy.admins.features.dashboard.data.remote.dto.AcademyResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.ClassroomResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.CourseResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.EnrollmentResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.ScheduleResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.StudentResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.TeacherResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.TransactionResourceDto
import javax.inject.Inject

/**
 * Implementation of the dashboard remote data source.
 *
 * Fetches dashboard data from the API using safe API calls.
 *
 * @property api The dashboard API interface.
 * @author Salim Ramirez
 */
class DashboardRemoteDataSourceImpl @Inject constructor(
    private val api: DashboardApi
) : DashboardRemoteDataSource {

    override suspend fun fetchCurrentAcademy(): AcademyResourceDto {
        return safeApiCall(endpoint = "academies/current") {
            api.getCurrentAcademy()
        }
    }

    override suspend fun fetchAllTransactions(): List<TransactionResourceDto> {
        return safeApiCall(endpoint = "transactions") {
            api.getAllTransactions()
        }
    }

    override suspend fun fetchAllTeachers(): List<TeacherResourceDto> {
        return safeApiCall(endpoint = "teachers") {
            api.getAllTeachers()
        }
    }

    override suspend fun fetchAllStudents(): List<StudentResourceDto> {
        return safeApiCall(endpoint = "students") {
            api.getAllStudents()
        }
    }

    override suspend fun fetchAllEnrollments(): List<EnrollmentResourceDto> {
        return safeApiCall(endpoint = "enrollments") {
            api.getAllEnrollments()
        }
    }

    override suspend fun fetchAllSchedules(): List<ScheduleResourceDto> {
        return safeApiCall(endpoint = "schedules") {
            api.getAllSchedules()
        }
    }

    override suspend fun fetchAllCourses(): List<CourseResourceDto> {
        return safeApiCall(endpoint = "courses") {
            api.getAllCourses()
        }
    }

    override suspend fun fetchAllClassrooms(): List<ClassroomResourceDto> {
        return safeApiCall(endpoint = "classrooms") {
            api.getAllClassrooms()
        }
    }
}

