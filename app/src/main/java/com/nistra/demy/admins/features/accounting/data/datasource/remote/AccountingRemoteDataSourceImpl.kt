package com.nistra.demy.admins.features.accounting.data.datasource.remote

import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.accounting.data.remote.api.AccountingApi
import com.nistra.demy.admins.features.accounting.data.remote.dto.TransactionResourceDto
import com.nistra.demy.admins.features.accounting.data.remote.dto.UpdateTransactionRequestDto
import okhttp3.ResponseBody
import javax.inject.Inject

class AccountingRemoteDataSourceImpl @Inject constructor(
    private val api: AccountingApi
) : AccountingRemoteDataSource {

    override suspend fun fetchTransactions(): List<TransactionResourceDto> {
        return safeApiCall(endpoint = "transactions") { api.getAllTransactions() }
    }

    override suspend fun fetchTransactionById(transactionId: Long): TransactionResourceDto {
        return safeApiCall(endpoint = "transactions/$transactionId") {
            api.getTransactionById(transactionId)
        }
    }

    override suspend fun updateTransaction(
        transactionId: Long,
        request: UpdateTransactionRequestDto
    ): TransactionResourceDto {
        return safeApiCall(endpoint = "transactions/$transactionId") {
            api.updateTransaction(transactionId, request)
        }
    }

    override suspend fun deleteTransaction(transactionId: Long) {
        return safeApiCall(endpoint = "transactions/$transactionId") {
            api.deleteTransaction(transactionId)
        }
    }

    override suspend fun exportTransactionsToPdf(): ResponseBody {
        return safeApiCall(endpoint = "reports/transactions/pdf") {
            api.exportTransactionsToPdf()
        }
    }

    override suspend fun exportTransactionsToExcel(): ResponseBody {
        return safeApiCall(endpoint = "reports/transactions/excel") {
            api.exportTransactionsToExcel()
        }
    }
}

