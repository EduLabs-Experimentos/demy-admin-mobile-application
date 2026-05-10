package com.nistra.demy.admins.features.billing.data.remote.dto

import com.nistra.demy.admins.features.invoicing.data.remote.dto.InvoiceResourceDto

data class BillingAccountResourceDto(
    val id: String,
    val studentId: String,
    val dniNumber: String,
    val academyId: String,
    val invoices: List<InvoiceResourceDto>
)
