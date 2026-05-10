package com.nistra.demy.admins.features.finance.domain.model

data class Transaction(
    val id: String,
    val type: String,
    val category: String,
    val method: String,
    val amount: String,
    val currency: String,
    val description: String?,
    val date: String
)
