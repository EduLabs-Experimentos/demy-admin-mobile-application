package com.nistra.demy.admins.features.billing.data.remote.dto

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CreateBillingAccountRequestDto(
    val studentId: String
)
