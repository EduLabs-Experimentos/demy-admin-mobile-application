package com.nistra.demy.admins.features.billing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.common.LocalizedString
import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.core.common.SnackbarType
import com.nistra.demy.admins.features.billing.domain.usecase.GetBillingAccountsUseCase

import com.nistra.demy.admins.features.billing.presentation.state.RegisterBillingAccountUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillingAccountsViewModel @Inject constructor(
    private val getBillingAccountsUseCase: GetBillingAccountsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterBillingAccountUiState())
    val uiState: StateFlow<RegisterBillingAccountUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadBillingAccounts()
    }


    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        filterBillingAccounts(query)
    }

    private fun filterBillingAccounts(query: String) {
        val allAccounts = _uiState.value.billingAccounts
        val filtered = if (query.isBlank()) {
            allAccounts
        } else {
            allAccounts.filter { account ->
                account.dniNumber.contains(query, ignoreCase = true)
            }
        }
        _uiState.update { it.copy(filteredBillingAccounts = filtered) }
    }

    fun loadBillingAccounts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingBillingAccounts = true) }

            getBillingAccountsUseCase()
                .onSuccess { accounts ->
                    _uiState.update {
                        it.copy(
                            billingAccounts = accounts,
                            filteredBillingAccounts = accounts,
                            isLoadingBillingAccounts = false
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoadingBillingAccounts = false,
                            snackbarMessage = SnackbarMessage(
                                message = error.message?.let { msg ->
                                    LocalizedString.Raw(msg)
                                } ?: LocalizedString.Resource(R.string.billing_error_load_failed),
                                type = SnackbarType.ERROR,
                                actionLabel = LocalizedString.Resource(R.string.action_ok)
                            )
                        )
                    }
                }
        }
    }

    fun clearSnackbarMessage() {
        _uiState.update { it.copy(snackbarMessage = null) }
    }
}
