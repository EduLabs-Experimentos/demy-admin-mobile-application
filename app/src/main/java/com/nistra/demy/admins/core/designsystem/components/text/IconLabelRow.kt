package com.nistra.demy.admins.core.designsystem.components.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A generic component that displays an icon alongside text.
 *
 * Useful for displaying labeled information with consistent spacing and styling.
 *
 * @param icon The icon to display.
 * @param text The text to display next to the icon.
 * @param modifier Optional [Modifier] for the row.
 * @param iconSize Size of the icon.
 * @param iconColor Color of the icon.
 * @param textStyle Style for the text.
 * @param textColor Color of the text.
 * @param spacing Horizontal spacing between icon and text.
 * @param contentDescription Content description for the icon (accessibility).
 * @author Salim Ramirez
 */
@Composable
fun IconLabelRow(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    iconSize: Dp = 16.dp,
    iconColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
    spacing: Dp = 8.dp,
    contentDescription: String? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = iconColor,
            modifier = Modifier.size(iconSize)
        )
        Text(
            text = text,
            style = textStyle,
            color = textColor
        )
    }
}

