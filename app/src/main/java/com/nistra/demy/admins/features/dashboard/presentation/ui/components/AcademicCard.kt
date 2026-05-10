package com.nistra.demy.admins.features.dashboard.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.theme.extendedColors

@Composable
fun LabeledValue(
    label: String,
    value: String,
    valueSize: TextStyle = MaterialTheme.typography.headlineSmall
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = valueSize,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun CardContainer(
    title: String,
    modifier: Modifier = Modifier,
    minHeight: Dp = 240.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.heightIn(min = minHeight),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.extendedColors.warning.colorContainer.copy(alpha = 0.2f)
        ),
        border = CardDefaults.outlinedCardBorder(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                content()
            }
        )
    }
}
