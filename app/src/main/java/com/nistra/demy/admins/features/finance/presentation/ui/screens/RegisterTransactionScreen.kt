package com.nistra.demy.admins.features.finance.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.feedback.DemySnackbarHost
import com.nistra.demy.admins.core.designsystem.components.feedback.SnackbarEffect
import com.nistra.demy.admins.core.designsystem.components.feedback.rememberDemySnackbarState
import com.nistra.demy.admins.features.finance.presentation.ui.components.FinanceAnalyticsPanel
import com.nistra.demy.admins.features.finance.presentation.ui.components.FinanceHeader
import com.nistra.demy.admins.features.finance.presentation.ui.components.TransactionRegistrationForm
import com.nistra.demy.admins.features.finance.presentation.viewmodel.RegisterTransactionViewModel

/**
 * Screen for registering new transactions.
 *
 * Displays a header with background image and a form for transaction registration.
 *
 * @param viewModel The ViewModel managing the screen state and business logic.
 * @author Salim Ramirez
 */
@Composable
fun FinanceScreen(
    viewModel: RegisterTransactionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val formData by viewModel.formData.collectAsState()
    val chartData by viewModel.chartData.collectAsState()
    val snackbarState = rememberDemySnackbarState()

    SnackbarEffect(
        message = uiState.snackbarMessage,
        snackbarState = snackbarState,
        onMessageShown = viewModel::clearSnackbarMessage
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FinanceHeader(
                title = stringResource(R.string.finance_screen_title),
                description = stringResource(R.string.finance_screen_description)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TransactionRegistrationForm(
                    formData = formData,
                    onFormChange = viewModel::onFieldChange,
                    onSubmit = viewModel::registerTransaction,
                    modifier = Modifier.weight(1f),
                    isLoading = uiState.isLoading
                )

                FinanceAnalyticsPanel(
                    chartData = chartData,
                    isLoading = uiState.isLoadingTransactions,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        DemySnackbarHost(
            snackbarState = snackbarState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true, widthDp = 1200, heightDp = 800)
@Composable
fun FinanceScreenPreview() {
    MaterialTheme {
        FinanceScreen()
    }
}

