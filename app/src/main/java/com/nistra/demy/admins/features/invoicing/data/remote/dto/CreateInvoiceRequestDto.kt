package com.nistra.demy.admins.features.invoicing.data.remote.dto

import com.squareup.moshi.JsonClass

/**
 * Data Transfer Object for creating a new invoice.
 * This is sent as the body of the POST request.
 * It does NOT include fields like 'id', 'status', or 'billingAccountId',
 * as those are generated or known by the server.
 */
@JsonClass(generateAdapter = true)
data class CreateInvoiceRequestDto(
    val invoiceType: String,
    val amount: String,
    val currency: String,
    val description: String,
    val issueDate: String,
    val dueDate: String,
    val status:String
)
