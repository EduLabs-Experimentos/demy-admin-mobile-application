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
import com.nistra.demy.admins.features.auth.presentation.ui.components.CompleteAccountForm
import com.nistra.demy.admins.features.auth.presentation.viewmodel.CompleteAccountViewModel

@Composable
fun CompleteAccountScreen(
    onAccountCompletedSuccess: () -> Unit,
    viewModel: CompleteAccountViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val formData by viewModel.formData.collectAsState()

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onAccountCompletedSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthHeader(
            title = stringResource(R.string.complete_account_header_title),
            subtitle = stringResource(R.string.complete_account_header_subtitle)
        )

        Spacer(modifier = Modifier.height(16.dp))

        CompleteAccountForm(
            formData = formData,
            isLoading = uiState.isLoading,
            onFirstNameChange = viewModel::onFirstNameChange,
            onLastNameChange = viewModel::onLastNameChange,
            onCountryCodeChange = viewModel::onCountryCodeChange,
            onPhoneNumberChange = viewModel::onPhoneNumberChange,
            onDniNumberChange = viewModel::onDniNumberChange,
            onCompleteClick = viewModel::completeAccount
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
