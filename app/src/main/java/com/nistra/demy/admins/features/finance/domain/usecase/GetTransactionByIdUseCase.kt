package com.nistra.demy.admins.features.finance.domain.usecase

import com.nistra.demy.admins.features.finance.domain.model.Transaction
import com.nistra.demy.admins.features.finance.domain.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionByIdUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(transactionId: Long): Result<Transaction> {
        return runCatching { repository.getTransactionById(transactionId) }
    }
}
