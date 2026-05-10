package com.nistra.demy.admins.features.auth.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.nistra.demy.admins.features.auth.presentation.ui.components.SignUpForm
import com.nistra.demy.admins.features.auth.presentation.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    onSignUpSuccess: (String) -> Unit,
    onGoToSignIn: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthHeader(
            title = stringResource(R.string.sign_up_header_title),
            subtitle = stringResource(R.string.sign_up_header_subtitle)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SignUpForm(
            email = uiState.email,
            password = uiState.password,
            isLoading = uiState.isLoading,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onSignUpClick = { viewModel.signUp(onSignUpSuccess) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthFooter(
            text = stringResource(R.string.sign_up_footer_text),
            actionText = stringResource(R.string.sign_up_footer_action_text),
            onActionClick = onGoToSignIn
        )
    }
}
