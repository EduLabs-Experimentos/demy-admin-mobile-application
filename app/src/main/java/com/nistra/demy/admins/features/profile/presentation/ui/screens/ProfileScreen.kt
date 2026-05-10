package com.nistra.demy.admins.features.profile.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.layout.ImageHeaderSection
import com.nistra.demy.admins.features.profile.presentation.viewmodel.ProfileViewModel
import com.nistra.demy.admins.features.profile.presentation.state.ProfileUiState

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is ProfileUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ProfileUiState.Error -> {
            val message = (uiState as ProfileUiState.Error).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            }
        }

        is ProfileUiState.Success -> {
            val stats = (uiState as ProfileUiState.Success).stats

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ImageHeaderSection(
                    title = "See and edit your profile information",
                    description = "Manage your personal data and academy details",
                    backgroundImage = R.drawable.profile_management_header_photo,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Left column (Account status + Motivation)
                    Column(
                        modifier = Modifier
                            .weight(0.9f)
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        AccountStatusCard(stats.accountStatus)
                        MotivationCard()
                    }

                    ProfileInfoCard(
                        general = stats.generalInfo,
                        academy = stats.academyInfo,
                        modifier = Modifier.weight(1.1f)
                    )
                }
            }
        }
    }
}
