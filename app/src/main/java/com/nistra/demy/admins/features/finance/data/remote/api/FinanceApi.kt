package com.nistra.demy.admins.features.finance.data.remote.api

import com.nistra.demy.admins.features.finance.data.remote.dto.RegisterTransactionRequestDto
import com.nistra.demy.admins.features.finance.data.remote.dto.TransactionResourceDto
import com.nistra.demy.admins.features.finance.data.remote.dto.UpdateTransactionRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FinanceApi {

    @POST("transactions")
    suspend fun registerTransaction(@Body request: RegisterTransactionRequestDto): TransactionResourceDto

    @GET("transactions")
    suspend fun getAllTransactions(): List<TransactionResourceDto>

    @GET("transactions/{transactionId}")
    suspend fun getTransactionById(
        @Path("transactionId") transactionId: Long
    ): TransactionResourceDto

    @PUT("transactions/{transactionId}")
    suspend fun updateTransaction(
        @Path("transactionId") transactionId: Long, @Body request: UpdateTransactionRequestDto
    ): TransactionResourceDto

    @DELETE("transactions/{transactionId}")
    suspend fun deleteTransaction(
        @Path("transactionId") transactionId: Long
    )
}
