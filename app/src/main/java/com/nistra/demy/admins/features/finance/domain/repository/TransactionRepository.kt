package com.nistra.demy.admins.features.finance.domain.repository

import com.nistra.demy.admins.features.finance.domain.model.Transaction

interface TransactionRepository {
    suspend fun registerTransaction(transaction: Transaction): Transaction

    suspend fun getAllTransactions(): List<Transaction>

    suspend fun getTransactionById(transactionId: Long): Transaction

    suspend fun updateTransaction(transactionId: Long, transaction: Transaction): Transaction

    suspend fun deleteTransaction(transactionId: Long)
}
