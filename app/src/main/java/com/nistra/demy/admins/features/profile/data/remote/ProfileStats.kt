package com.nistra.demy.admins.features.profile.data.remote

import com.nistra.demy.admins.features.profile.domain.model.AcademyInfo
import com.nistra.demy.admins.features.profile.domain.model.GeneralInfo

data class ProfileStats(
    val accountStatus: String,
    val slogan: String?,
    val generalInfo: GeneralInfo,
    val academyInfo: AcademyInfo
)
