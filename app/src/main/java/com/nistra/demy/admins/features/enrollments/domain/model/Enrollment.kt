package com.nistra.demy.admins.features.enrollments.domain.model

data class Enrollment(
    val id: Long = 0L,
    val studentId: Long,
    val periodId: Long,
    val scheduleId: Long,
    val amount: String,
    val currency: String,
    val paymentStatus: PaymentStatus,
    val enrollmentStatus: EnrollmentStatus? = null,

    val studentName: String = "",
    val periodName: String = "",
    val scheduleName: String = ""
)
