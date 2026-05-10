package com.nistra.demy.admins.core.designsystem.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.theme.DemyTheme

@Composable
fun ThemePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Display Large – Montserrat Regular",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Headline Medium – Montserrat Bold",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = "Title Small – Montserrat Italic",
            style = MaterialTheme.typography.titleSmall.copy(fontStyle = FontStyle.Italic),
            color = MaterialTheme.colorScheme.tertiary
        )

        // --- BODY (Poppins) ---
        Text(
            text = "Body Large – Poppins Regular",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Body Medium – Poppins SemiBold",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Body Small – Poppins Italic",
            style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic),
            color = MaterialTheme.colorScheme.onBackground
        )

        // --- LABELS (Poppins) ---
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "Label Large",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Label Medium",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Label Small",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        // --- BOTONES ---
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Botón Primary")
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Botón Secondary")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LightPreview() {
    DemyTheme(darkTheme = false) {
        ThemePreview()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreview() {
    DemyTheme(darkTheme = true) {
        ThemePreview()
    }
}
