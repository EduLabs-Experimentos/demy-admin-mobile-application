package com.nistra.demy.admins.features.finance.data.datasource.remote

import com.nistra.demy.admins.features.finance.data.remote.dto.RegisterTransactionRequestDto
import com.nistra.demy.admins.features.finance.data.remote.dto.TransactionResourceDto
import com.nistra.demy.admins.features.finance.data.remote.dto.UpdateTransactionRequestDto

interface FinanceRemoteDataSource {

    suspend fun registerTransaction(
        request: RegisterTransactionRequestDto
    ): TransactionResourceDto

    suspend fun fetchTransactions(): List<TransactionResourceDto>

    suspend fun fetchTransactionById(
        transactionId: Long
    ): TransactionResourceDto

    suspend fun updateTransaction(
        transactionId: Long,
        request: UpdateTransactionRequestDto
    ): TransactionResourceDto

    suspend fun deleteTransaction(
        transactionId: Long
    )
}
