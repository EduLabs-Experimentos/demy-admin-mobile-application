package com.nistra.demy.admins.features.profile.domain.repository

import com.nistra.demy.admins.features.profile.data.remote.ProfileStats
import com.nistra.demy.admins.features.profile.domain.model.GeneralInfo

interface ProfileRepository {
    suspend fun fetchProfileInfo(): GeneralInfo
}
