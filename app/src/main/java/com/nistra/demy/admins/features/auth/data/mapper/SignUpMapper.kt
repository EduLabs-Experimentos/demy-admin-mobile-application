package com.nistra.demy.admins.features.auth.data.mapper

import com.nistra.demy.admins.features.auth.data.remote.dto.SignUpResponseDto
import com.nistra.demy.admins.features.auth.domain.model.UserSession

fun SignUpResponseDto.toDomain(): UserSession {
    return UserSession(
        id = this.id,
        email = this.emailAddress,
        token = null
    )
}
