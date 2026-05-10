package com.nistra.demy.admins.features.settings.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nistra.demy.admins.core.designsystem.components.cards.ListItemCard
@Composable
fun SettingsItem(
    title: String,
    description: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f),
    switchColor: Color = MaterialTheme.colorScheme.primary
) {
    ListItemCard(
        onClick = { onCheckedChange(!isChecked) },
        modifier = Modifier.fillMaxWidth(),
        containerColor = containerColor,
        mainContent = {
            Column {
                Text(title, style = MaterialTheme.typography.titleSmall)
                Text(description, style = MaterialTheme.typography.bodySmall)
            }
        },
        actions = {
            Switch(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = switchColor,
                    uncheckedTrackColor = MaterialTheme.colorScheme.outline,
                    uncheckedThumbColor = Color.White
                )
            )
        }
    )
}
