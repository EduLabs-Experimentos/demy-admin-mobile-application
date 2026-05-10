package com.nistra.demy.admins.features.auth.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle

@Composable
fun AuthFooter(
    text: String,
    actionText: String,
    onActionClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        val annotated = buildAnnotatedString {
            append(text)
            withLink(
                LinkAnnotation.Clickable(
                    tag = "signup_link",
                    linkInteractionListener = { onActionClick() }
                )
            ) {
                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(actionText)
                }
            }
        }

        Text(
            text = annotated,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
