package com.nistra.demy.admins.features.auth.data.mapper

import com.nistra.demy.admins.features.auth.data.remote.dto.VerifyAccountResponseDto
import com.nistra.demy.admins.features.auth.domain.model.UserSession

fun VerifyAccountResponseDto.toDomain(): UserSession {
    return UserSession(
        id = this.id,
        email = this.emailAddress,
        token = this.token,
    )
}
