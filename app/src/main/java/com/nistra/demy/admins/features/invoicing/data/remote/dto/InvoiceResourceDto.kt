package com.nistra.demy.admins.features.invoicing.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InvoiceResourceDto(
    val id: String,
    val invoiceType: String,
    val amount: String,
    val currency: String,
    val description: String,
    val issueDate: String,
    val dueDate: String,
    val status: String
)
