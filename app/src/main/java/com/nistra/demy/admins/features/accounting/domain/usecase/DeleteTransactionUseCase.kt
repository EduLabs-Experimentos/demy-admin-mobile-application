package com.nistra.demy.admins.features.accounting.domain.usecase

import com.nistra.demy.admins.features.accounting.domain.repository.TransactionRepository
import javax.inject.Inject

class DeleteTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(transactionId: Long): Result<Unit> {
        return runCatching { repository.deleteTransaction(transactionId) }
    }
}

