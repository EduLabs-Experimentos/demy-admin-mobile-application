package com.nistra.demy.admins.features.finance.data.datasource.remote

import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.finance.data.remote.api.FinanceApi
import com.nistra.demy.admins.features.finance.data.remote.dto.RegisterTransactionRequestDto
import com.nistra.demy.admins.features.finance.data.remote.dto.TransactionResourceDto
import com.nistra.demy.admins.features.finance.data.remote.dto.UpdateTransactionRequestDto
import javax.inject.Inject

class FinanceRemoteDataSourceImpl @Inject constructor(
    private val api: FinanceApi
) : FinanceRemoteDataSource {
    override suspend fun registerTransaction(request: RegisterTransactionRequestDto): TransactionResourceDto {
        return safeApiCall(endpoint = "transactions") { api.registerTransaction(request) }
    }

    override suspend fun fetchTransactions(): List<TransactionResourceDto> {
        return safeApiCall(endpoint = "transactions") { api.getAllTransactions() }
    }

    override suspend fun fetchTransactionById(transactionId: Long): TransactionResourceDto {
        return safeApiCall(endpoint = "transactions/$transactionId") { api.getTransactionById(transactionId) }
    }

    override suspend fun updateTransaction(
        transactionId: Long,
        request: UpdateTransactionRequestDto
    ): TransactionResourceDto {
        return safeApiCall(endpoint = "transactions/$transactionId") { api.updateTransaction(transactionId, request) }
    }

    override suspend fun deleteTransaction(transactionId: Long) {
        return safeApiCall(endpoint = "transactions/$transactionId") { api.deleteTransaction(transactionId) }
    }
}
