package com.nistra.demy.admins.features.finance.data.repository

import com.nistra.demy.admins.features.finance.data.datasource.remote.FinanceRemoteDataSource
import com.nistra.demy.admins.features.finance.data.mapper.toDomain
import com.nistra.demy.admins.features.finance.data.mapper.toRequestDto
import com.nistra.demy.admins.features.finance.data.mapper.toUpdateRequestDto
import com.nistra.demy.admins.features.finance.domain.model.Transaction
import com.nistra.demy.admins.features.finance.domain.repository.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val remoteDataSource: FinanceRemoteDataSource
) : TransactionRepository {
    override suspend fun registerTransaction(transaction: Transaction): Transaction {
        val registeredTransactionResourceDto = remoteDataSource.registerTransaction(transaction.toRequestDto())
        return registeredTransactionResourceDto.toDomain()
    }

    override suspend fun getAllTransactions(): List<Transaction> {
        val transactionsResponse = remoteDataSource.fetchTransactions()
        return transactionsResponse.map { it.toDomain() }
    }

    override suspend fun getTransactionById(transactionId: Long): Transaction {
        val transactionResourceDto = remoteDataSource.fetchTransactionById(transactionId)
        return transactionResourceDto.toDomain()
    }

    override suspend fun updateTransaction(transactionId: Long, transaction: Transaction): Transaction {
        val updatedTransactionResourceDto = remoteDataSource.updateTransaction(transactionId, transaction.toUpdateRequestDto())
        return updatedTransactionResourceDto.toDomain()
    }

    override suspend fun deleteTransaction(transactionId: Long) {
        remoteDataSource.deleteTransaction(transactionId)
    }
}
