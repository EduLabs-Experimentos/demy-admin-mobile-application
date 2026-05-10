package com.nistra.demy.admins.features.help.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCardNoTitle

@Composable
fun HelpCard(
    modifier: Modifier = Modifier
) {
    val poppinsBold = FontFamily(Font(R.font.poppins_bold))
    val poppinsRegular = FontFamily(Font(R.font.poppins_regular))
    val moradoContainer = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f)

    InfoCard(
        title = "Help",
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InfoCardNoTitle(
                modifier = Modifier.fillMaxWidth(),
                containerColor = moradoContainer
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Report a bug",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = poppinsRegular,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                        )
                    )
                    Text(
                        text = "If you’ve found a bug or something isn’t working as expected, please report it. Your feedback helps us improve and provide a better experience for everyone.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = poppinsRegular,
                        )
                    )
                }
            }

            InfoCardNoTitle(
                modifier = Modifier.fillMaxWidth(),
                containerColor = moradoContainer
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Contribute",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = poppinsRegular,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                        )
                    )
                    Text(
                        text = "Want to contribute to the project? Whether it’s ideas, code, or suggestions, your input is welcome and helps us grow.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = poppinsRegular,
                        )
                    )
                }
            }
        }
    }
}
