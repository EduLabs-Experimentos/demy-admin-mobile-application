package com.nistra.demy.admins.features.profile.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nistra.demy.admins.R

@Composable
fun AccountStatusCard(
    accountStatus: String,
    modifier: Modifier = Modifier
) {
    val poppinsBold = FontFamily(Font(R.font.poppins_bold))
    val poppinsRegular = FontFamily(Font(R.font.poppins_regular))

    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 170.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 1f)
        ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Account status",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = poppinsRegular,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontSize = 17.sp
                )
            )

            Column {
                Text(
                    text = "Role",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        fontFamily = poppinsRegular,
                        fontSize = 13.sp
                    )
                )
                Text(
                    text = "ADMINISTRATOR",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        fontFamily = poppinsBold,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp
                    )
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Status",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            fontFamily = poppinsRegular,
                            fontSize = 13.sp
                        )
                    )
                    Text(
                        text = accountStatus.uppercase(),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            fontFamily = poppinsBold,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 22.sp
                        )
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Verification",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            fontFamily = poppinsRegular,
                            fontSize = 13.sp
                        )
                    )
                    Text(
                        text = "VERIFIED",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            fontFamily = poppinsBold,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 22.sp
                        )
                    )
                }
            }

            Column {
                Text(
                    text = "Academies",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        fontFamily = poppinsRegular,
                        fontSize = 13.sp
                    )
                )
                Text(
                    text = "1",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        fontFamily = poppinsBold,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp
                    )
                )
            }
        }
    }
}
