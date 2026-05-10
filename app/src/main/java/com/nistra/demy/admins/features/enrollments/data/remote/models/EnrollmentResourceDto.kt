package com.nistra.demy.admins.features.enrollments.data.remote.models

data class EnrollmentResourceDto(
    val id: Long,
    val studentId: Long,
    val periodId: Long,
    val scheduleId: Long,
    val amount: String,
    val currency: String,
    val paymentStatus: String,
    val enrollmentStatus: String,

    // Información adicional para mostrar en UI
    val studentName: String?,
    val periodName: String?,
    val scheduleName: String?
)
