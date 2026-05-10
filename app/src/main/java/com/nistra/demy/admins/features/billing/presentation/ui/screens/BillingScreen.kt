package com.nistra.demy.admins.features.billing.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.nistra.demy.admins.core.designsystem.components.feedback.DemySnackbarHost
import com.nistra.demy.admins.core.designsystem.components.feedback.SnackbarEffect
import com.nistra.demy.admins.core.designsystem.components.feedback.rememberDemySnackbarState
import com.nistra.demy.admins.features.billing.presentation.ui.components.BillingAccountsHeader
import com.nistra.demy.admins.features.billing.presentation.ui.components.BillingSearchPanel
import com.nistra.demy.admins.features.billing.presentation.viewmodel.BillingAccountsViewModel
import com.nistra.demy.admins.R

@Composable
fun BillingScreen(
    viewModel: BillingAccountsViewModel = hiltViewModel(),
    onGoToDetails:(String) -> Unit,
) {


    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val snackbarState = rememberDemySnackbarState()


    SnackbarEffect(
        message = uiState.snackbarMessage,
        snackbarState = snackbarState,
        onMessageShown = viewModel::clearSnackbarMessage
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp), // Padding general
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BillingAccountsHeader(
                title = stringResource(R.string.billing_screen_title),
                description = stringResource(R.string.billing_screen_description)
            )


            BillingSearchPanel(
                searchQuery = searchQuery,
                onSearchQueryChange = viewModel::onSearchQueryChange,
                billingAccounts = uiState.filteredBillingAccounts,
                modifier = Modifier.weight(1f),
                isLoading = uiState.isLoadingBillingAccounts,
                onItemClick = onGoToDetails,
            )
        }

        DemySnackbarHost(
            snackbarState = snackbarState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


@Preview(showBackground = true, widthDp = 1200, heightDp = 800)
@Composable
fun RegisterBillingScreenPreview() {
    MaterialTheme {
        BillingScreen(onGoToDetails = {})
    }
}
