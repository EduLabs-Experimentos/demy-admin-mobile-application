package com.nistra.demy.admins.features.accounting.data.repository

import android.os.Environment
import com.nistra.demy.admins.features.accounting.data.datasource.remote.AccountingRemoteDataSource
import com.nistra.demy.admins.features.accounting.data.mapper.toDomain
import com.nistra.demy.admins.features.accounting.data.mapper.toUpdateRequestDto
import com.nistra.demy.admins.features.accounting.domain.model.Transaction
import com.nistra.demy.admins.features.accounting.domain.repository.TransactionRepository
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val remoteDataSource: AccountingRemoteDataSource
) : TransactionRepository {

    override suspend fun getAllTransactions(): List<Transaction> {
        val transactionsResponse = remoteDataSource.fetchTransactions()
        return transactionsResponse.map { it.toDomain() }
    }

    override suspend fun getTransactionById(transactionId: Long): Transaction {
        val transactionResourceDto = remoteDataSource.fetchTransactionById(transactionId)
        return transactionResourceDto.toDomain()
    }

    override suspend fun updateTransaction(transactionId: Long, transaction: Transaction): Transaction {
        val requestDto = transaction.toUpdateRequestDto()
        val responseDto = remoteDataSource.updateTransaction(transactionId, requestDto)
        return responseDto.toDomain()
    }

    override suspend fun deleteTransaction(transactionId: Long) {
        remoteDataSource.deleteTransaction(transactionId)
    }

    override suspend fun exportTransactionsToPdf(): File {
        val responseBody = remoteDataSource.exportTransactionsToPdf()
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "demy_transactions_$timestamp.pdf"
        return saveFile(responseBody.bytes(), fileName)
    }

    override suspend fun exportTransactionsToExcel(): File {
        val responseBody = remoteDataSource.exportTransactionsToExcel()
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "demy_transactions_$timestamp.xlsx"
        return saveFile(responseBody.bytes(), fileName)
    }

    private fun saveFile(bytes: ByteArray, fileName: String): File {
        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(downloadsDir, fileName)
        FileOutputStream(file).use { output ->
            output.write(bytes)
        }
        return file
    }
}

