package com.nistra.demy.admins.features.auth.data.datasource.remote

import com.nistra.demy.admins.core.network.dto.MessageResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.CompleteAccountResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SetUpAcademyResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignUpResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.VerifyAccountResponseDto

interface AuthRemoteDataSource {
    suspend fun signIn(emailAddress: String, password: String): SignInResponseDto

    suspend fun signUp(emailAddress: String, password: String, roles: List<String>): SignUpResponseDto

    suspend fun verifyAccount(email: String, code: String): VerifyAccountResponseDto

    suspend fun completeAccount(
        firstName: String,
        lastName: String,
        countryCode: String,
        phoneNumber: String,
        dniNumber: String,
        userId: Long
    ): CompleteAccountResponseDto

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
    ): SetUpAcademyResponseDto
}
