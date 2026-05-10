package com.nistra.demy.admins.features.help.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nistra.demy.admins.R

@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    val poppinsRegular = FontFamily(Font(R.font.poppins_regular))
    val poppinsBold = FontFamily(Font(R.font.poppins_bold))

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Help",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Back to Help",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = poppinsRegular,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }

            Text(
                text = "Privacy Policy",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontFamily = poppinsBold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Text(
                text = "Last updated: November 2025",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = poppinsRegular,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )

            Text(
                text = """
1. Introduction  
At **Demy**, we value your privacy and are committed to protecting your personal information.  
This Privacy Policy explains how we collect, use, store, and protect your data when you use our academic management platform.

2. Information We Collect  
We may collect the following types of information:  
• Personal data (name, email address, phone number).  
• Academic information (attendance, grades, courses).  
• Usage data (IP address, device type, browser, activity within the platform).  
• Billing and payment information, in case of subscription plans.  

3. Use of Information  
The collected information is used to:  
• Provide, maintain, and improve our services.  
• Manage user accounts and process payments.  
• Personalize the user experience.  
• Send important notifications about the platform or updates.  
• Comply with legal and security obligations.  

4. Data Retention  
Your data will be securely stored while your account is active or as required by law.  
You may request the deletion of your information by writing to our contact email.

5. Information Security  
We implement appropriate technical and organizational measures to protect your data against unauthorized access, alteration, disclosure, or destruction.  
However, no system is completely secure; by using Demy, you acknowledge and accept this risk.

6. Information Sharing  
We do not sell or share your information with third parties, except in the following cases:  
• Compliance with laws or judicial requirements.  
• Payment processing through certified providers.  
• Cases where you have given explicit consent.  

7. User Rights  
You have the right to:  
• Access the personal data we hold about you.  
• Request its correction or deletion.  
• Object to the processing of your data in certain cases.  
• Withdraw your consent at any time.  

8. Cookies and Similar Technologies  
Demy uses cookies to enhance user experience, remember preferences, and analyze system usage.  
You can configure your browser to reject cookies, although some functions may be affected.

9. Changes to the Privacy Policy  
We may update this policy at any time. If significant changes are made, users will be notified in advance.  
Continued use of our services implies acceptance of the revised policy.

10. Contact  
If you have questions about this Privacy Policy or wish to exercise your rights, please contact us at:  
 privacy@demy.com  
 +51 927 230 192
                """.trimIndent(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = poppinsRegular,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 22.sp
                )
            )
        }
    }
}
