package com.nistra.demy.admins.core.designsystem.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * A generic list item card component with customizable content and actions.
 *
 * Provides a consistent container for list items with main content area
 * and optional action buttons.
 *
 * @param onClick Callback invoked when the card is clicked.
 * @param modifier Optional [Modifier] for the card.
 * @param containerColor Background color of the card.
 * @param contentSpacing Vertical spacing between content items in dp.
 * @param mainContent The main composable content to display.
 * @param actions Optional composable for action buttons (e.g., edit, delete).
 * @author Salim Ramirez
 */
@Composable
fun ListItemCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    contentSpacing: Int = 8,
    mainContent: @Composable () -> Unit,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(contentSpacing.dp)
            ) {
                mainContent()
            }

            actions?.let {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    content = it
                )
            }
        }
    }
}

