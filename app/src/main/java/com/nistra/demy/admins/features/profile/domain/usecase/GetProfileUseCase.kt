package com.nistra.demy.admins.features.profile.domain.usecase

import com.nistra.demy.admins.features.profile.data.remote.ProfileStats
import com.nistra.demy.admins.features.profile.domain.repository.AcademyRepository
import com.nistra.demy.admins.features.profile.domain.repository.ProfileRepository
import jakarta.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val academyRepository: AcademyRepository
) {
    suspend operator fun invoke(): ProfileStats {
        val profileInfo = profileRepository.fetchProfileInfo()
        val academyInfo = academyRepository.fetchAcademyInfo()
        return ProfileStats(
            accountStatus = "ACTIVE",
            slogan = academyInfo.description,
            generalInfo = profileInfo,
            academyInfo = academyInfo
        )
    }
}
