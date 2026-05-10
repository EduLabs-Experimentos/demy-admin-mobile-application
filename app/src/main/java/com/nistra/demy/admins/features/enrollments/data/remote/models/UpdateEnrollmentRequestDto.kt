package com.nistra.demy.admins.features.enrollments.data.remote.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateEnrollmentRequestDto(
    val amount: String,
    val currency: String,
    val enrollmentStatus: String,
    val paymentStatus: String
)
