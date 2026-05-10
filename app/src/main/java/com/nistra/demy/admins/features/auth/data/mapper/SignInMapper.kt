package com.nistra.demy.admins.features.auth.data.mapper

import com.nistra.demy.admins.features.auth.data.remote.dto.SignInResponseDto
import com.nistra.demy.admins.features.auth.domain.model.UserSession

fun SignInResponseDto.toDomain(): UserSession {
    return UserSession(
        id = this.id,
        email = this.emailAddress,
        token = this.token
    )
}
