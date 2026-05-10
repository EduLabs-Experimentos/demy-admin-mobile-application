package com.nistra.demy.admins.features.profile.data.repository

import com.nistra.demy.admins.features.profile.data.remote.api.ProfileApi
import com.nistra.demy.admins.features.profile.domain.model.GeneralInfo
import com.nistra.demy.admins.features.profile.domain.repository.ProfileRepository
import jakarta.inject.Inject
import kotlinx.coroutines.delay

class ProfileRepositoryImpl @Inject constructor(
    private val api: ProfileApi
) : ProfileRepository {

    override suspend fun fetchProfileInfo(): GeneralInfo {
        val dto = api.getCurrentAdministrator()
        return GeneralInfo(
            name = dto.firstName,
            lastName = dto.lastName,
            dni = dto.dniNumber,
            email = dto.emailAddress,
            phone = dto.phoneNumber ?: "-"
        )
    }
}
