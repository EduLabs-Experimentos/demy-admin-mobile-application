package com.nistra.demy.admins.features.auth.domain.usecase

import com.nistra.demy.admins.features.auth.domain.model.UserSession
import com.nistra.demy.admins.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        emailAddress: String,
        password: String,
        roles: List<String>
    ): Result<UserSession> {
        return runCatching {
            repository.signUp(
                emailAddress = emailAddress,
                password = password,
                roles = roles
            )
        }
    }
}
