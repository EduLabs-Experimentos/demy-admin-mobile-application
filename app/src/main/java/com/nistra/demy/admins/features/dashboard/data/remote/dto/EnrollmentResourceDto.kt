package com.nistra.demy.admins.features.dashboard.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EnrollmentResourceDto(
    val id: Long,
    val studentId: Long,
    val periodId: Long,
    val scheduleId: Long,
    val amount: String,
    val currency: String,
    val paymentStatus: String,
    val enrollmentStatus: String,
    val studentName: String?,
    val periodName: String?,
    val scheduleName: String?
)
