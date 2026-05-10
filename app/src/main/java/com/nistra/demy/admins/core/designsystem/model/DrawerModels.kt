package com.nistra.demy.admins.core.designsystem.model

import androidx.annotation.StringRes
import com.nistra.demy.admins.core.navigation.model.DrawerDestination

data class UserUi(
    val name: String,
    val role: String,
    val avatarResId: Int? = null
)

data class DrawerSection(
    @param:StringRes val headerResId: Int,
    val items: List<DrawerDestination>
)
