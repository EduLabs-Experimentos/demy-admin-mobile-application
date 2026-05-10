package com.nistra.demy.admins.features.finance.domain.usecase

import com.nistra.demy.admins.features.finance.domain.model.Transaction
import com.nistra.demy.admins.features.finance.domain.repository.TransactionRepository
import javax.inject.Inject

class GetAllTransactionsUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(): Result<List<Transaction>> {
        return runCatching { repository.getAllTransactions() }
    }
}
