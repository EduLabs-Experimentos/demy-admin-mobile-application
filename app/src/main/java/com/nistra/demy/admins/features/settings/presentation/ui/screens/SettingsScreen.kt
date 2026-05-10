package com.nistra.demy.admins.features.settings.presentation.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.features.settings.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val isDarkTheme = isSystemInDarkTheme()
    val themeText = if (isDarkTheme) "Dark" else "Light"
    val locale = LocalConfiguration.current.locales[0].language
    val languageText = when (locale) {
        "es" -> "Español"
        "en" -> "English"
        else -> "System"
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        val isWideScreen = maxWidth >= 900.dp

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Intro card
            InfoCard(
                title = "Adjust your preferences",
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ) {
                Text(
                    text = "Settings are a set of preferences that you can customize to enhance your experience. " +
                        "You can modify settings such as theme, notifications, privacy, and more.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (isWideScreen) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        AccessibilityTestCard()
                        ThemeConfigurationCard(viewModel, uiState, themeText, isDarkTheme)
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        GeneralSettingsCard(
                            viewModel,
                            uiState,
                            languageText,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                }
            } else {
                AccessibilityTestCard()
                ThemeConfigurationCard(viewModel, uiState, themeText, isDarkTheme)
                GeneralSettingsCard(viewModel, uiState, languageText)
            }
        }
    }
}
