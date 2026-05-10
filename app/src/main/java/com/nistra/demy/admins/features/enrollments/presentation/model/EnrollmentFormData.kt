package com.nistra.demy.admins.features.enrollments.presentation.model
data class EnrollmentFormData(
    val studentId: Long? = null,
    val periodId: Long? = null,
    val scheduleId: Long? = null,
    val amount: String = "",
    val currency: String = "PEN",
    val paymentStatus: String = "",
    val enrollmentStatus: String = ""
) {
    val isFormValid: Boolean
        get() = studentId != null &&
            periodId != null &&
            scheduleId != null &&
            amount.isNotBlank() &&
            currency.isNotBlank() &&
            paymentStatus.isNotBlank()

    fun hasData(): Boolean {
        return studentId != null ||
            periodId != null ||
            scheduleId != null ||
            amount.isNotBlank() ||
            currency.isNotBlank() ||
            paymentStatus.isNotBlank() ||
            enrollmentStatus.isNotBlank()
    }
}
