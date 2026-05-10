package com.nistra.demy.admins.features.auth.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.auth.presentation.ui.components.AuthFooter
import com.nistra.demy.admins.features.auth.presentation.ui.components.AuthHeader
import com.nistra.demy.admins.features.auth.presentation.ui.components.SignInForm
import com.nistra.demy.admins.features.auth.presentation.viewmodel.SignInViewModel

@Composable
fun SignInScreen(
    onLoggedIn: () -> Unit,
    onGoToSignUp: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthHeader(
            title = stringResource(R.string.sign_in_header_title),
            subtitle = stringResource(R.string.sign_in_header_subtitle)
        )

        Spacer(modifier = Modifier.height(32.dp))

        SignInForm(
            username = uiState.username,
            password = uiState.password,
            isLoading = uiState.isLoading,
            onUsernameChange = viewModel::onUsernameChange,
            onPasswordChange = viewModel::onPasswordChange,
            onSignInClick = { viewModel.signIn(onLoggedIn) }
        )

        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }

        uiState.errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        AuthFooter(
            text = stringResource(R.string.sign_in_footer_text),
            actionText = stringResource(R.string.sign_in_footer_action_text),
            onActionClick = onGoToSignUp
        )
    }
}
