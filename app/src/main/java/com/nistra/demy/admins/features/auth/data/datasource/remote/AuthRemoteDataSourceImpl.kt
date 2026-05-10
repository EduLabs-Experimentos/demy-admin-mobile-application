package com.nistra.demy.admins.features.auth.data.datasource.remote

import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.auth.data.remote.api.AuthApi
import com.nistra.demy.admins.features.auth.data.remote.dto.CompleteAccountRequestDto
import com.nistra.demy.admins.features.auth.data.remote.dto.CompleteAccountResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SetUpAcademyRequestDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SetUpAcademyResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInRequestDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignUpRequestDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignUpResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.VerifyAccountRequestDto
import com.nistra.demy.admins.features.auth.data.remote.dto.VerifyAccountResponseDto
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val api : AuthApi
) : AuthRemoteDataSource {
    override suspend fun signIn(emailAddress: String, password: String): SignInResponseDto {
        val request = SignInRequestDto(emailAddress, password)
        return safeApiCall(endpoint = "authentication/sign-in") { api.signIn(request) }
    }

    override suspend fun signUp(
        emailAddress: String,
        password: String,
        roles: List<String>
    ): SignUpResponseDto {
        val request = SignUpRequestDto(emailAddress, password, roles)
        return safeApiCall(endpoint = "authentication/sign-up") { api.signUp(request) }
    }

    override suspend fun verifyAccount(email: String, code: String): VerifyAccountResponseDto {
        val request = VerifyAccountRequestDto(email, code)
        return safeApiCall(endpoint = "authentication/verify") { api.verifyAccount(request) }
    }

    override suspend fun completeAccount(
        firstName: String,
        lastName: String,
        countryCode: String,
        phoneNumber: String,
        dniNumber: String,
        userId: Long
    ): CompleteAccountResponseDto {
        val request = CompleteAccountRequestDto(
            firstName = firstName,
            lastName = lastName,
            countryCode = countryCode,
            phoneNumber = phoneNumber,
            dniNumber = dniNumber,
            userId = userId
        )
        return safeApiCall(endpoint = "administrators") { api.completeAccount(request) }
    }

    override suspend fun setUpAcademy(
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
    ): SetUpAcademyResponseDto {
        val request = SetUpAcademyRequestDto(
            academyName,
            academyDescription,
            street,
            district,
            province,
            department,
            emailAddress,
            countryCode,
            phone,
            ruc,
            administratorId
        )
        return safeApiCall(endpoint = "academies") {
            api.setUpAcademy(request)
        }
    }
}
