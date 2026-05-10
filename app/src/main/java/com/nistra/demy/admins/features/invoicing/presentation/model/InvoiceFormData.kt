package com.nistra.demy.admins.features.invoicing.presentation.model

data class InvoiceFormData(
    val invoiceType: String = "STUDENT_ENROLLMENT",
    val amount: String = "",
    val currency: String = "PEN",
    val description: String = " ",
    val issueDate: String = " ",
    val dueDate: String = " ",
    val status: String = " "
)
