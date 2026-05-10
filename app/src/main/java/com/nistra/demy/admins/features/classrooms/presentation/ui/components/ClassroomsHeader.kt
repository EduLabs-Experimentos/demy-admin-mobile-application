package com.nistra.demy.admins.features.classrooms.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.layout.ImageHeaderSection

@Composable
fun ClassroomsHeader(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    ImageHeaderSection(
        title = title,
        description = description,
        backgroundImage = R.drawable.classrooms_management_header_photo,
        modifier = modifier
    )
}
