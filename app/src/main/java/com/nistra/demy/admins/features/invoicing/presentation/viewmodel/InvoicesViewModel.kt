package com.nistra.demy.admins.features.invoicing.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.common.LocalizedString
import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.features.invoicing.domain.usecase.DeleteInvoiceUseCase
import com.nistra.demy.admins.features.invoicing.domain.usecase.GetInvoicesByStudentDniUseCase
import com.nistra.demy.admins.features.invoicing.domain.usecase.MarkInvoiceAsPaidUseCase
import com.nistra.demy.admins.features.invoicing.presentation.state.InvoicesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoicesViewModel @Inject constructor(
    private val getInvoicesByStudentDniUseCase: GetInvoicesByStudentDniUseCase,
    private val markInvoiceAsPaidUseCase: MarkInvoiceAsPaidUseCase,
    private val deleteInvoiceUseCase: DeleteInvoiceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(InvoicesUiState())
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun searchInvoices() {
        val billingAccountId = _searchQuery.value.trim()
        if (billingAccountId.isBlank()) {
            _uiState.update {
                it.copy(invoices = emptyList(),
                    snackbarMessage = SnackbarMessage(
                        message = LocalizedString.Resource(R.string.invoices_search_validation_empty)
                    )
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getInvoicesByStudentDniUseCase(billingAccountId)
                .onSuccess { invoices ->
                    _uiState.update { it.copy(isLoading = false, invoices = invoices) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(isLoading = false,
                            invoices = emptyList(),
                            snackbarMessage = SnackbarMessage(
                                message = LocalizedString.Resource(R.string.invoices_search_error)
                            )
                        )
                    }
                }
        }
    }

    fun markAsPaid(invoiceId: String, billingAccountId: String) {
        if (billingAccountId.isBlank()) return

        viewModelScope.launch {
            markInvoiceAsPaidUseCase(billingAccountId, invoiceId)
                .onSuccess {
                    searchInvoices()
                    _uiState.update { it.copy(
                        snackbarMessage = SnackbarMessage(
                            message = LocalizedString.Resource(R.string.invoices_mark_as_paid_success)
                        )
                    )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            snackbarMessage = SnackbarMessage(
                                message = LocalizedString.Resource(R.string.invoices_mark_as_paid_error)
                            )
                        )
                    }
                }
        }
    }


    fun deleteInvoice(invoiceId: String, billingAccountId: String) {
        if (billingAccountId.isBlank()) return

        viewModelScope.launch {
            deleteInvoiceUseCase(billingAccountId, invoiceId)
                .onSuccess {
                    searchInvoices()
                    _uiState.update {
                        it.copy(snackbarMessage = SnackbarMessage(
                            message = LocalizedString.Resource(R.string.invoices_delete_success)
                        ))
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(snackbarMessage = SnackbarMessage(
                            message = LocalizedString.Resource(R.string.invoices_delete_error)
                        ))
                    }
                }
        }
    }

    fun clearSnackbarMessage() {
        _uiState.update { it.copy(snackbarMessage = null) }
    }
}
