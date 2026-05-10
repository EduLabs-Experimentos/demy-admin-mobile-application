package com.nistra.demy.admins.features.settings.presentation.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCardNoTitle

@Composable
fun AccessibilityTestCard() {
    InfoCardNoTitle(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        Text(
            text = "Accessibility Test",
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.SemiBold
            )
        )

        Text(
            text = "Adjust the accessibility options to reflect changes in this block.",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )

        listOf(
            MaterialTheme.typography.bodySmall,
            MaterialTheme.typography.bodyMedium,
            MaterialTheme.typography.bodyLarge,
            MaterialTheme.typography.titleMedium,
            MaterialTheme.typography.titleLarge
        ).forEach { style ->
            Text(
                text = "This is how the text will look in the application.",
                style = style.copy(color = MaterialTheme.colorScheme.onPrimaryContainer)
            )
        }
    }
}
