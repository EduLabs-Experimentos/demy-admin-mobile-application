package com.nistra.demy.admins.features.auth.domain.repository

import com.nistra.demy.admins.features.auth.domain.model.UserSession

interface AuthRepository {
    suspend fun signIn(emailAddress: String, password: String): UserSession

    suspend fun signUp(
        emailAddress: String,
        password: String,
        roles: List<String>
    ): UserSession

    suspend fun verifyAccount(email: String, code: String): UserSession

    suspend fun completeAccount(
        firstName: String,
        lastName: String,
        countryCode: String,
        phoneNumber: String,
        dniNumber: String,
        userId: Long
    ): Long

    suspend fun setUpAcademy(
        academyName: String,
        academyDescription: String,
        street: String,
        district: String,
        province: String,
        department: String,
        emailAddress: String,
        countryCode: String,
        phone: String,
        ruc: String,
        administratorId: Long
    ): Long
}
