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
fun TermsAndConditionsScreen(navController: NavController) {
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
                text = "Terms and Conditions",
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
1. Acceptance of Terms  
By accessing and using the **Demy** platform, you agree to be bound by these terms and conditions of use.  
Demy is an academic management platform designed to optimize the administration of academies, educational centers, and teaching organizations.

2. Service Description  
Demy provides academic management tools that include:  
• Management of enrollments and students  
• Class and schedule control  
• Payment and billing administration  
• Academic tracking and reporting  
• Communication with students and parents  
• Management of teachers and administrative staff  

3. User Accounts  
To access Demy’s services, you must create an account by providing accurate and up-to-date information.  
You are responsible for maintaining the confidentiality of your password and all activities that occur under your account.  
You must notify us immediately of any unauthorized use of your account.

4. Acceptable Use  
You agree to use Demy solely for legitimate academic management purposes. It is prohibited to:  
• Use the platform for illegal or unauthorized activities  
• Interfere with the normal operation of the platform  
• Attempt to access other users’ accounts  
• Share false or misleading information  
• Violate intellectual property rights  

5. Data and Content  
You retain all rights to the academic data you enter into Demy.  
By using our services, you grant us a limited license to process, store, and back up your data solely to provide the service.  
We are committed to maintaining the confidentiality of sensitive academic information.

6. Billing and Payments  
Demy’s services are available under monthly or annual subscription plans.  
Payments are processed securely and automatically renewed unless you cancel your subscription.  
You can cancel your subscription at any time from your control panel.

7. Limitation of Liability  
Demy is provided “as is.” We do not guarantee that the service will be error-free or uninterrupted.  
Our liability is limited to the amount paid for the service during the 12 months preceding the event that gave rise to the claim.

8. Termination  
We may suspend or terminate your access to Demy if you violate these terms.  
Upon termination, your data will remain available for export for 30 days, after which it will be permanently deleted.

9. Modifications  
We reserve the right to modify these terms.  
Significant changes will be notified 30 days in advance. Continued use of the service constitutes acceptance of the new terms.

10. Contact  
For inquiries about these terms or our services, contact us at:  
 contact_us@demy.com  
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
