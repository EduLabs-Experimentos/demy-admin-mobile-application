package com.nistra.demy.admins.features.enrollments.data.remote.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CreateEnrollmentRequestDto(
    val studentId: Long,
    val periodId: Long,
    val scheduleId: Long,
    val amount: String,
    val currency: String,
    val paymentStatus: String
)
