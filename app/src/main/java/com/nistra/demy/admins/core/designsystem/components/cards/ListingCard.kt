package com.nistra.demy.admins.core.designsystem.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A generic card component for displaying a list of items with a header.
 * This is a simpler version of SearchableListCard, without the search field.
 *
 * @param title The title of the card.
 * @param description A short description under the title.
 * @param modifier Optional Modifier for the card.
 * @param isLoading Whether the list is currently loading.
 * @param emptyMessage The message to display when the list is empty.
 * @param itemCount The total number of items in the list.
 * @param itemCountLabel The label to display next to the item count.
 * @param listContent The Composable content of the list, defined using LazyListScope.
 */
@Composable
fun ListingCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    emptyMessage: String = "No items found.",
    itemCount: Int = 0,
    itemCountLabel: String? = null,
    listContent: LazyListScope.() -> Unit
) {
    ElevatedCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Column {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else if (itemCount == 0) {
                    Text(
                        text = emptyMessage,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        content = listContent
                    )
                }
            }


            itemCountLabel?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}
