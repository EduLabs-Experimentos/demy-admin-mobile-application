package com.nistra.demy.admins.core.designsystem.components.table

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Header row for a data table.
 *
 * @param modifier Optional [Modifier] for the component.
 * @param content The header cells content.
 * @author Salim Ramirez
 */
@Composable
fun TableHeaderRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            ),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

/**
 * Header cell for a data table.
 *
 * @param text The header text.
 * @param modifier Optional [Modifier] for the component.
 * @param weight The weight of the column.
 * @author Salim Ramirez
 */
@Composable
fun RowScope.TableHeaderCell(
    text: String,
    modifier: Modifier = Modifier,
    weight: Float = 1f
) {
    Box(
        modifier = modifier
            .weight(weight)
            .padding(12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

