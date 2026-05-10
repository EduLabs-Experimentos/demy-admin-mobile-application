package com.nistra.demy.admins.features.accounting.data.remote.api

import com.nistra.demy.admins.features.accounting.data.remote.dto.TransactionResourceDto
import com.nistra.demy.admins.features.accounting.data.remote.dto.UpdateTransactionRequestDto
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Streaming

interface AccountingApi {

    @GET("transactions")
    suspend fun getAllTransactions(): List<TransactionResourceDto>

    @GET("transactions/{transactionId}")
    suspend fun getTransactionById(
        @Path("transactionId") transactionId: Long
    ): TransactionResourceDto

    @PUT("transactions/{transactionId}")
    suspend fun updateTransaction(
        @Path("transactionId") transactionId: Long,
        @Body request: UpdateTransactionRequestDto
    ): TransactionResourceDto

    @DELETE("transactions/{transactionId}")
    suspend fun deleteTransaction(
        @Path("transactionId") transactionId: Long
    )

    @Streaming
    @Headers("Accept: application/pdf")
    @GET("reports/transactions/pdf")
    suspend fun exportTransactionsToPdf(): ResponseBody

    @Streaming
    @Headers("Accept: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @GET("reports/transactions/excel")
    suspend fun exportTransactionsToExcel(): ResponseBody
}

