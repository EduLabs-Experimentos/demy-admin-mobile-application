package com.nistra.demy.admins.core.designsystem.components.text

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

/**
 * A composable that displays a label-value pair vertically.
 *
 * Commonly used for displaying labeled data in cards and forms.
 *
 * @param label The label text displayed above the value.
 * @param value The value text displayed below the label.
 * @param modifier Modifier to be applied to the column.
 * @param valueStyle Text style for the value text.
 * @author Salim Ramirez
 */
@Composable
fun LabeledValue(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    valueStyle: TextStyle = MaterialTheme.typography.headlineSmall
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = valueStyle,
            fontWeight = FontWeight.SemiBold
        )
    }
}

