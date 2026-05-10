package com.nistra.demy.admins.features.profile.data.repository

import com.nistra.demy.admins.features.profile.data.remote.api.ProfileApi
import com.nistra.demy.admins.features.profile.domain.model.AcademyInfo
import com.nistra.demy.admins.features.profile.domain.repository.AcademyRepository
import jakarta.inject.Inject
import kotlinx.coroutines.delay

class AcademyRepositoryImpl @Inject constructor(
    private val api: ProfileApi
) : AcademyRepository {

    override suspend fun fetchAcademyInfo(): AcademyInfo {
        val dto = api.getCurrentAcademy()
        return AcademyInfo(
            name = dto.academyName,
            address = buildString {
                append(dto.street ?: "")
                if (!dto.district.isNullOrBlank()) append(", ${dto.district}")
                if (!dto.province.isNullOrBlank()) append(", ${dto.province}")
                if (!dto.department.isNullOrBlank()) append(", ${dto.department}")
            },
            ruc = dto.ruc ?: "-",
            email = dto.emailAddress ?: "-",
            phone = dto.phoneNumber ?: "-",
            description = dto.academyName
        )
    }
}
