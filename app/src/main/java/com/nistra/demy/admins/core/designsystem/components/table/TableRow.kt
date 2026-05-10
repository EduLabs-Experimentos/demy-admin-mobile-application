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
import androidx.compose.ui.unit.dp

/**
 * Data row for a table.
 *
 * @param modifier Optional [Modifier] for the component.
 * @param content The row cells content.
 * @author Salim Ramirez
 */
@Composable
fun TableRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
            ),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

/**
 * Data cell for a table.
 *
 * @param text The cell text.
 * @param modifier Optional [Modifier] for the component.
 * @param weight The weight of the column.
 * @author Salim Ramirez
 */
@Composable
fun RowScope.TableCell(
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
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

/**
 * Action cell for a table with custom content.
 *
 * @param modifier Optional [Modifier] for the component.
 * @param weight The weight of the column.
 * @param content The action content (e.g., buttons, icons).
 * @author Salim Ramirez
 */
@Composable
fun RowScope.TableActionCell(
    modifier: Modifier = Modifier,
    weight: Float = 1f,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .weight(weight)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

