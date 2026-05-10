package com.nistra.demy.admins.features.accounting.domain.usecase

import com.nistra.demy.admins.features.accounting.domain.model.Transaction
import com.nistra.demy.admins.features.accounting.domain.repository.TransactionRepository
import javax.inject.Inject

/**
 * Use case for updating an existing transaction.
 *
 * @property repository The transaction repository.
 * @author Salim Ramirez
 */
class UpdateTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(transactionId: Long, transaction: Transaction): Result<Transaction> {
        return runCatching { repository.updateTransaction(transactionId, transaction) }
    }
}

