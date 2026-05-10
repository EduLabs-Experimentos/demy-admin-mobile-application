package com.nistra.demy.admins.core.designsystem.components.layout

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * A generic header section component with a background image and text overlay.
 *
 * Displays a title and optional description over a background image with
 * a dark overlay for better text contrast and accessibility.
 *
 * @param title The main title text to display.
 * @param description Optional description text to display below the title.
 * @param backgroundImage Drawable resource ID for the background image.
 * @param modifier Optional [Modifier] for the component.
 * @param overlayAlpha Alpha value for the dark overlay (0.0 to 1.0).
 * @param titleColor Color for the title text.
 * @param descriptionColor Color for the description text.
 * @author Salim Ramirez
 */
@Composable
fun ImageHeaderSection(
    title: String,
    description: String? = null,
    @DrawableRes backgroundImage: Int,
    modifier: Modifier = Modifier,
    overlayAlpha: Float = 0.5f,
    titleColor: Color = Color.White,
    descriptionColor: Color = Color.White.copy(alpha = 0.95f)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    ) {
        // Background image
        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )

        // Dark overlay for better text contrast and a11y
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = overlayAlpha))
        )

        // Content
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = titleColor
            )

            description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    color = descriptionColor,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

