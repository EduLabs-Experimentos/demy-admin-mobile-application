package com.nistra.demy.admins.features.help.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCardNoTitle

@Composable
fun LegalCard(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val poppinsBold = FontFamily(Font(R.font.poppins_bold))
    val poppinsRegular = FontFamily(Font(R.font.poppins_regular))
    val naranjaContainer = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)

    InfoCard(
        title = "Legal",
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InfoCardNoTitle(
                containerColor = naranjaContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("terms_and_conditions") }
                    .padding(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Terms & Conditions",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontFamily = poppinsRegular,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                        )
                    )
                    Text(
                        text = "By using our services, you agree to our Terms and Conditions. Tap to read the complete version.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = poppinsRegular,
                        )
                    )
                }
            }

            InfoCardNoTitle(
                containerColor = naranjaContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("privacy_policy") }
                    .padding(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Privacy Policy",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontFamily = poppinsRegular,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                        )
                    )
                    Text(
                        text = "Learn how we handle and protect your personal information. Tap to read our privacy policy.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = poppinsRegular,
                        )
                    )
                }
            }
        }
    }
}
