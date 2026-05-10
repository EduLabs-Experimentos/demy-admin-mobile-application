package com.nistra.demy.admins.features.accounting.domain.repository

import com.nistra.demy.admins.features.accounting.domain.model.Transaction
import java.io.File

interface TransactionRepository {
    suspend fun getAllTransactions(): List<Transaction>

    suspend fun getTransactionById(transactionId: Long): Transaction

    suspend fun updateTransaction(transactionId: Long, transaction: Transaction): Transaction

    suspend fun deleteTransaction(transactionId: Long)

    suspend fun exportTransactionsToPdf(): File

    suspend fun exportTransactionsToExcel(): File
}

