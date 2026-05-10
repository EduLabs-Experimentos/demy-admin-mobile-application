package com.nistra.demy.admins.features.finance.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterTransactionResponseDto(
    val id: String,
    @field:Json(name = "transactionType") val type: String,
    @field:Json(name = "transactionCategory") val category: String,
    @field:Json(name = "transactionMethod") val method: String,
    val amount: String,
    val currency: String,
    val description: String?,
    @field:Json(name = "transactionDate") val date: String
)
