package com.nistra.demy.admins.features.teachers.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.layout.ImageHeaderSection

/**
 * Header section specifically for the teachers feature.
 *
 * This is a wrapper around the generic ImageHeaderSection component
 * that provides the teacher-specific background image.
 *
 * @param title The title text to display.
 * @param description The description text to display.
 * @param modifier Optional [Modifier] for the component.
 * @author Salim Ramirez
 */
@Composable
fun TeachersHeader(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    ImageHeaderSection(
        title = title,
        description = description,
        backgroundImage = R.drawable.teachers_management_header_photo,
        modifier = modifier
    )
}

