package com.nistra.demy.admins.features.dashboard.data.remote.api

import com.nistra.demy.admins.features.dashboard.data.remote.dto.AcademyResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.ClassroomResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.CourseResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.EnrollmentResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.StudentResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.TeacherResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.TransactionResourceDto
import com.nistra.demy.admins.features.dashboard.data.remote.dto.ScheduleResourceDto
import retrofit2.http.GET

interface DashboardApi {
    @GET("academies/current")
    suspend fun getCurrentAcademy(): AcademyResourceDto

    @GET("transactions")
    suspend fun getAllTransactions(): List<TransactionResourceDto>

    @GET("teachers")
    suspend fun getAllTeachers(): List<TeacherResourceDto>

    @GET("students")
    suspend fun getAllStudents(): List<StudentResourceDto>

    @GET("enrollments")
    suspend fun getAllEnrollments(): List<EnrollmentResourceDto>

    @GET("schedules")
    suspend fun getAllSchedules(): List<ScheduleResourceDto>

    @GET("courses")
    suspend fun getAllCourses(): List<CourseResourceDto>

    @GET("classrooms")
    suspend fun getAllClassrooms(): List<ClassroomResourceDto>
}
