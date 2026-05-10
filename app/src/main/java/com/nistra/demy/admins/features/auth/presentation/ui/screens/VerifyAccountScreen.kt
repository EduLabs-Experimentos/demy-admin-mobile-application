package com.nistra.demy.admins.features.auth.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.auth.presentation.ui.components.AuthHeader
import com.nistra.demy.admins.features.auth.presentation.ui.components.VerifyAccountForm
import com.nistra.demy.admins.features.auth.presentation.viewmodel.VerifyAccountViewModel

@Composable
fun VerifyAccountScreen(
    email: String,
    onVerifiedSuccess: () -> Unit,
    viewModel: VerifyAccountViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) onVerifiedSuccess()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthHeader(
            title = stringResource(R.string.verify_account_header_title),
            subtitle = stringResource(R.string.verify_account_header_subtitle)
        )

        Spacer(modifier = Modifier.height(16.dp))

        VerifyAccountForm(
            isLoading = uiState.isLoading,
            onVerifyClick = { code ->
                viewModel.verifyAccount(email, code)
            }
        )

        if (uiState.errorMessage != null) {
            Text(
                text = uiState.errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
