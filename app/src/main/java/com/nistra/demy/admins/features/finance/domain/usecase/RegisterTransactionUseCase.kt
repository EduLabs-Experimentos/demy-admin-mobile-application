package com.nistra.demy.admins.features.finance.domain.usecase

import com.nistra.demy.admins.features.finance.domain.model.Transaction
import com.nistra.demy.admins.features.finance.domain.repository.TransactionRepository
import javax.inject.Inject

class RegisterTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(transaction: Transaction): Result<Transaction> {
        return runCatching { repository.registerTransaction(transaction) }
    }
}
