package com.nistra.demy.admins.features.auth.data.repository

import com.nistra.demy.admins.features.auth.data.datasource.remote.AuthRemoteDataSource
import com.nistra.demy.admins.features.auth.data.mapper.toDomain
import com.nistra.demy.admins.features.auth.domain.model.UserSession
import com.nistra.demy.admins.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun signIn(emailAddress: String, password: String): UserSession {
        val response = remoteDataSource.signIn(emailAddress, password)
        return response.toDomain()
    }

    override suspend fun signUp(emailAddress: String, password: String, roles: List<String>): UserSession {
        val response = remoteDataSource.signUp(emailAddress, password, roles)
        return response.toDomain()
    }

    override suspend fun verifyAccount(email: String, code: String): UserSession {
        val response = remoteDataSource.verifyAccount(email, code)
        return response.toDomain()
    }

    override suspend fun completeAccount(
        firstName: String,
        lastName: String,
        countryCode: String,
        phoneNumber: String,
        dniNumber: String,
        userId: Long
    ): Long {
        val response = remoteDataSource.completeAccount(
            firstName,
            lastName,
            countryCode,
            phoneNumber,
            dniNumber,
            userId
        )
        return response.id
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
    ): Long {
        val response = remoteDataSource.setUpAcademy(
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
        return response.id
    }
}
