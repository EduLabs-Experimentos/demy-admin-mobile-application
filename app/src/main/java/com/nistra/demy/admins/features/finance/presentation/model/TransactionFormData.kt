package com.nistra.demy.admins.features.finance.presentation.model

import com.nistra.demy.admins.features.finance.domain.model.Currency
import com.nistra.demy.admins.features.finance.domain.model.PaymentMethod
import com.nistra.demy.admins.features.finance.domain.model.TransactionCategory
import com.nistra.demy.admins.features.finance.domain.model.TransactionType

data class TransactionFormData(
    val type: TransactionType? = null,
    val category: TransactionCategory? = null,
    val method: PaymentMethod? = null,
    val amount: String = "",
    val currency: Currency = Currency.PEN,
    val description: String = "",
    val date: String = ""
)

