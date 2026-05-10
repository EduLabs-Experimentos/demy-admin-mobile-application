package com.nistra.demy.admins.features.enrollments.data.mapper

import com.nistra.demy.admins.features.enrollments.data.remote.models.CreateEnrollmentRequestDto
import com.nistra.demy.admins.features.enrollments.data.remote.models.EnrollmentResourceDto
import com.nistra.demy.admins.features.enrollments.data.remote.models.UpdateEnrollmentRequestDto
import com.nistra.demy.admins.features.enrollments.domain.model.Enrollment
import com.nistra.demy.admins.features.enrollments.domain.model.EnrollmentStatus
import com.nistra.demy.admins.features.enrollments.domain.model.PaymentStatus

fun EnrollmentResourceDto.toDomain(): Enrollment {
    return Enrollment(
        id = this.id,
        studentId = this.studentId,
        periodId = this.periodId,
        scheduleId = this.scheduleId,
        amount = this.amount,
        currency = this.currency,
        paymentStatus = PaymentStatus.valueOf(this.paymentStatus.uppercase()),
        enrollmentStatus = EnrollmentStatus.valueOf(this.enrollmentStatus.uppercase()),
        studentName = this.studentName ?: "",
        periodName = this.periodName ?: "",
        scheduleName = this.scheduleName ?: ""
    )
}

fun Enrollment.toCreateEnrollmentRequestDto(): CreateEnrollmentRequestDto {
    return CreateEnrollmentRequestDto(
        studentId = this.studentId,
        periodId = this.periodId,
        scheduleId = this.scheduleId,
        amount = this.amount,
        currency = this.currency,
        paymentStatus = this.paymentStatus.name
    )
}

fun Enrollment.toUpdateEnrollmentRequestDto(): UpdateEnrollmentRequestDto {
    return UpdateEnrollmentRequestDto(
        amount = this.amount,
        currency = this.currency,
        enrollmentStatus = this.enrollmentStatus?.name ?: "ACTIVE",
        paymentStatus = this.paymentStatus.name
    )
}
