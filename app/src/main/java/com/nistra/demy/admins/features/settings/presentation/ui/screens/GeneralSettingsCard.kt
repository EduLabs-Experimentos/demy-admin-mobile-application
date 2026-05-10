package com.nistra.demy.admins.features.settings.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.components.cards.ListItemCard
import com.nistra.demy.admins.features.settings.domain.model.SettingsUiState
import com.nistra.demy.admins.features.settings.presentation.ui.components.SettingsItem
import com.nistra.demy.admins.features.settings.presentation.viewmodel.SettingsViewModel

@Composable
fun GeneralSettingsCard(
    viewModel: SettingsViewModel,
    uiState: SettingsUiState,
    languageText: String,
    modifier: Modifier = Modifier
) {
    InfoCard(
        title = "General",
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxHeight()
    ) {
        SettingsItem(
            title = "Notifications",
            description = "Receive updates and alerts.",
            isChecked = uiState.notificationsEnabled,
            onCheckedChange = viewModel::toggleNotifications,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f),
            switchColor = MaterialTheme.colorScheme.tertiary
        )

        SettingsItem(
            title = "Marketing",
            description = "Allow promotional messages and emails.",
            isChecked = uiState.marketingEnabled,
            onCheckedChange = viewModel::toggleMarketing,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f),
            switchColor = MaterialTheme.colorScheme.tertiary
        )

        ListItemCard(
            onClick = { /**/ },
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f),
            mainContent = {
                Column {
                    Text("Change password", style = MaterialTheme.typography.titleSmall)
                    Text(
                        "Update your account credentials.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )

        ListItemCard(
            onClick = { /**/ },
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f),
            mainContent = {
                Column {
                    Text("Language", style = MaterialTheme.typography.titleSmall)
                    Text(
                        "Choose your preferred interface language.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            actions = {
                AssistChip(
                    onClick = {},
                    label = { Text("System") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = "System icon",
                            tint = MaterialTheme.colorScheme.onTertiary
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        labelColor = MaterialTheme.colorScheme.onTertiary
                    )
                )
            }
        )

        ListItemCard(
            onClick = { /**/ },
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f),
            mainContent = {
                Column {
                    Text("Report a bug", style = MaterialTheme.typography.titleSmall)
                    Text(
                        "Help us improve the app.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )
    }
}
