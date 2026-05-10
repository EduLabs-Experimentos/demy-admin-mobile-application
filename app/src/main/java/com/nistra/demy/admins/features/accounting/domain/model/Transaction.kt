package com.nistra.demy.admins.features.accounting.domain.model

/**
 * Domain model representing a transaction in the accounting system.
 *
 * @property id Unique identifier for the transaction.
 * @property type Type of transaction (Income/Expense).
 * @property category Transaction category.
 * @property method Payment method used.
 * @property amount Transaction amount.
 * @property currency Currency code (e.g., PEN, USD).
 * @property description Optional description of the transaction.
 * @property date Date of the transaction.
 * @author Salim Ramirez
 */
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

