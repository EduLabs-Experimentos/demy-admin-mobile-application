package com.nistra.demy.admins.core.designsystem.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * A generic searchable list card component with a header and search functionality.
 *
 * Provides a consistent container for displaying searchable lists with loading states,
 * empty states, and custom content.
 *
 * @param title The title text to display in the header.
 * @param description Optional description text to display in the header.
 * @param searchQuery The current search query string.
 * @param onSearchQueryChange Callback invoked when the search query changes.
 * @param searchPlaceholder Placeholder text for the search input.
 * @param searchLabel Label text for the search input.
 * @param isLoading Whether the list is currently loading.
 * @param emptyMessage Message to display when the list is empty.
 * @param itemCount Number of items in the list.
 * @param itemCountLabel Label template for displaying item count (use %d for count).
 * @param modifier Optional [Modifier] for the card.
 * @param headerBackgroundColor Background color for the header section.
 * @param headerTextColor Color for the header text.
 * @param accentColor Accent color for search field focus states.
 * @param searchLeadingIcon Optional leading icon for the search field.
 * @param listContent The composable content for the list items.
 * @author Salim Ramirez
 */
@Composable
fun SearchableListCard(
    title: String,
    description: String?,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    searchPlaceholder: String,
    searchLabel: String,
    isLoading: Boolean,
    emptyMessage: String,
    itemCount: Int,
    itemCountLabel: String,
    modifier: Modifier = Modifier,
    headerBackgroundColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    headerTextColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
    accentColor: Color = MaterialTheme.colorScheme.tertiary,
    searchLeadingIcon: @Composable (() -> Unit)? = null,
    listContent: LazyListScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(headerBackgroundColor)
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = headerTextColor
                )

                description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = headerTextColor.copy(alpha = 0.8f),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // Search field
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                label = { Text(searchLabel) },
                leadingIcon = searchLeadingIcon,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text(searchPlaceholder) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = accentColor,
                    focusedLabelColor = accentColor,
                    focusedLeadingIconColor = accentColor,
                    cursorColor = accentColor
                )
            )

            // Loading, empty, or content state
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = accentColor)
                    }
                }
                itemCount == 0 -> {
                    Text(
                        text = emptyMessage,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
                else -> {
                    Text(
                        text = String.format(itemCountLabel, itemCount),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listContent()
                    }
                }
            }
        }
    }
}

