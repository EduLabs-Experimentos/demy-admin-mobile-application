package com.nistra.demy.admins.features.settings.presentation.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.features.settings.domain.model.SettingsUiState
import com.nistra.demy.admins.features.settings.presentation.ui.components.SettingsItem
import com.nistra.demy.admins.features.settings.presentation.viewmodel.SettingsViewModel

@Composable
fun ThemeConfigurationCard(
    viewModel: SettingsViewModel,
    uiState: SettingsUiState,
    themeText: String,
    isDarkTheme: Boolean
) {
    InfoCard(
        title = "Theme configuration",
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        SettingsItem(
            title = "Dark theme",
            description = "Current: $themeText mode (follows system theme).",
            isChecked = isDarkTheme,
            onCheckedChange = viewModel::toggleDarkTheme,
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f),
            switchColor = MaterialTheme.colorScheme.secondaryContainer
        )

        SettingsItem(
            title = "High contrast",
            description = "Improve visibility with higher contrast.",
            isChecked = uiState.highContrast,
            onCheckedChange = viewModel::toggleHighContrast,
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f),
            switchColor = MaterialTheme.colorScheme.secondaryContainer
        )
    }
}
