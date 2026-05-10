package com.nistra.demy.admins.features.auth.domain.usecase

import com.nistra.demy.admins.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class SetUpAcademyUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
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
    ): Result<Long> {
        return runCatching {
            val response: Long = repository.setUpAcademy(
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
            response
        }
    }
}
