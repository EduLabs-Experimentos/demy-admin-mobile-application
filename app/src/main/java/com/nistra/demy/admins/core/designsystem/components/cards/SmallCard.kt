package com.nistra.demy.admins.core.designsystem.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * A small card component displaying a label and a value.
 *
 * Designed for use in multiple card layouts.
 * Provides customization options for colors, dimensions, and optional icon.
 * Adapts to light and dark themes.
 *
 * @param label The label text to display.
 * @param value The value text to display.
 * @param modifier Optional [Modifier] for the card.
 * @param icon Optional icon to display next to the label.
 * @param containerColor Background color of the card.
 * @param contentColor Color for text and icon content.
 * @param borderColor Color of the card border.
 * @param valueColor Color of the value text (overrides contentColor if set).
 * @param labelColor Color of the label text (overrides contentColor if set).
 * @param minWidthDp Minimum width of the card in dp.
 * @param minHeightDp Minimum height of the card in dp.
 * @author Salim Ramirez
 */
@Composable
fun SmallCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    borderColor: Color = MaterialTheme.colorScheme.outlineVariant,
    valueColor: Color? = null,
    labelColor: Color? = null,
    minWidthDp: Int = 120,
    minHeightDp: Int = 100,
) {
    OutlinedCard(
        modifier = modifier.sizeIn(minWidth = minWidthDp.dp, minHeight = minHeightDp.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = containerColor),
        border = BorderStroke(1.dp, borderColor),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = labelColor ?: contentColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    color = labelColor ?: contentColor
                )
            }
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = valueColor ?: contentColor
            )
        }
    }
}
