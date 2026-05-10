package com.nistra.demy.admins.features.billing.domain.model

import com.nistra.demy.admins.features.invoicing.domain.model.Invoice

data class BillingAccount(
    val id: String,
    val studentId: String,
    val dniNumber: String,
    val invoices: List<Invoice>,
    val academyId: String

)
