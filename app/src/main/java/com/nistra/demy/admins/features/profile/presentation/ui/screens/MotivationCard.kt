package com.nistra.demy.admins.features.profile.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nistra.demy.admins.R
@Composable
fun MotivationCard(modifier: Modifier = Modifier) {
    val poppinsBold = FontFamily(Font(R.font.poppins_bold))
    val poppinsBoldItalic = FontFamily(Font(R.font.poppins_bolditalic))

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 38.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = poppinsBold,
                        color = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    append("HAVE A\n")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 46.sp,
                        fontWeight = FontWeight.Black,
                        fontStyle = FontStyle.Italic,
                        fontFamily = poppinsBoldItalic,
                        color = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    append(" GREAT DAY ")
                }
            }

            Text(
                text = text,
                textAlign = TextAlign.Center,
                lineHeight = 52.sp,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}
