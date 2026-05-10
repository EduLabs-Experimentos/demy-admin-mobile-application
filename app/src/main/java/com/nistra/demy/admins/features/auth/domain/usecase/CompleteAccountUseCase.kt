package com.nistra.demy.admins.features.auth.domain.usecase

import com.nistra.demy.admins.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class CompleteAccountUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        countryCode: String,
        phoneNumber: String,
        dniNumber: String,
        userId: Long
    ): Result<Long> {
        return runCatching {
            val response: Long = repository.completeAccount(
                firstName,
                lastName,
                countryCode,
                phoneNumber,
                dniNumber,
                userId
            )
            response
        }
    }
}
