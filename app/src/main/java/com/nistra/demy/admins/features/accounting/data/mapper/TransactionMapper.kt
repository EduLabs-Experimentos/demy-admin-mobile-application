package com.nistra.demy.admins.features.accounting.data.mapper

import com.nistra.demy.admins.features.accounting.data.remote.dto.TransactionResourceDto
import com.nistra.demy.admins.features.accounting.data.remote.dto.UpdateTransactionRequestDto
import com.nistra.demy.admins.features.accounting.domain.model.Transaction

fun TransactionResourceDto.toDomain(): Transaction {
    return Transaction(
        id = this.id,
        type = this.type,
        category = this.category,
        method = this.method,
        amount = this.amount,
        currency = this.currency,
        date = this.date,
        description = this.description
    )
}

fun Transaction.toUpdateRequestDto(): UpdateTransactionRequestDto {
    return UpdateTransactionRequestDto(
        type = this.type,
        category = this.category,
        method = this.method,
        amount = this.amount,
        currency = this.currency,
        description = this.description,
        date = this.date
    )
}


