package com.nistra.demy.admins.features.finance.domain.usecase

import com.nistra.demy.admins.features.finance.domain.model.Transaction
import com.nistra.demy.admins.features.finance.domain.repository.TransactionRepository
import javax.inject.Inject

class UpdateTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(transactionId: Long, transaction: Transaction): Result<Transaction> {
        return runCatching { repository.updateTransaction(transactionId, transaction) }
    }
}
