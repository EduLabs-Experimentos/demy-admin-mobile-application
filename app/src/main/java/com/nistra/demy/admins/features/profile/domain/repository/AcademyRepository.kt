package com.nistra.demy.admins.features.profile.domain.repository

import com.nistra.demy.admins.features.profile.domain.model.AcademyInfo

interface AcademyRepository {
    suspend fun fetchAcademyInfo(): AcademyInfo
}
