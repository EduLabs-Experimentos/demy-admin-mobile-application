package com.nistra.demy.admins.features.billing.presentation.viewmodel


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.core.common.SnackbarType
import com.nistra.demy.admins.features.billing.domain.usecase.AddInvoiceToBillingAccountUseCase
import com.nistra.demy.admins.features.billing.domain.usecase.GetBillingAccountByIdUseCase
import com.nistra.demy.admins.features.billing.presentation.navigation.BILLING_ACCOUNT_ID_ARG
import com.nistra.demy.admins.features.billing.presentation.state.BillingAccountDetailsUiState
import com.nistra.demy.admins.features.invoicing.domain.model.Invoice
import com.nistra.demy.admins.features.invoicing.presentation.model.InvoiceFormData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import com.nistra.demy.admins.core.common.LocalizedString
import com.nistra.demy.admins.features.invoicing.domain.usecase.DeleteInvoiceUseCase
import com.nistra.demy.admins.features.invoicing.domain.usecase.MarkInvoiceAsPaidUseCase

@HiltViewModel
class BillingAccountDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBillingAccountByIdUseCase: GetBillingAccountByIdUseCase,
    private val addInvoiceToBillingAccountUseCase: AddInvoiceToBillingAccountUseCase,
    private val deleteInvoiceUseCase: DeleteInvoiceUseCase,
    private val markInvoiceAsPaidUseCase: MarkInvoiceAsPaidUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BillingAccountDetailsUiState())
    val uiState = _uiState.asStateFlow()


    private val _invoiceFormData = MutableStateFlow(InvoiceFormData())
    val invoiceFormData = _invoiceFormData.asStateFlow()

    private val billingAccountId: String = savedStateHandle.get<String>(BILLING_ACCOUNT_ID_ARG)!!

    init {
        loadAccountDetails()
    }



    fun onShowAddInvoiceDialog(show: Boolean) {
        _uiState.update { it.copy(isAddInvoiceDialogVisible = show) }
        if (!show) {
            _invoiceFormData.value = InvoiceFormData()
        }
    }

    fun onInvoiceFormFieldChange(updatedData: InvoiceFormData) {
        _invoiceFormData.value = updatedData
    }



    fun onAddInvoiceConfirm() {
        val formData = _invoiceFormData.value


        if (formData.description.isBlank() ||
            formData.amount.isBlank() ||
            formData.currency.isBlank() ||
            formData.invoiceType.isBlank() ||
            formData.dueDate.isBlank())
        {
            _uiState.update {
                it.copy(snackbarMessage = SnackbarMessage(
                    message = LocalizedString.Resource(R.string.billing_add_invoice_validation_empty),
                    type = SnackbarType.ERROR
                ))
            }
            return
        }


        val amountWithDot = formData.amount.replace(',', '.')


        if (amountWithDot.toDoubleOrNull() == null) {
            _uiState.update {
                it.copy(snackbarMessage = SnackbarMessage(
                    message = LocalizedString.Resource(R.string.billing_add_invoice_validation_invalid_amount),
                    type = SnackbarType.ERROR
                ))
            }
            return
        }

        viewModelScope.launch {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val issueDateString = dateFormat.format(Calendar.getInstance().time)


            val newInvoice = Invoice(
                id = "",
                description = formData.description,

                amount = amountWithDot,
                currency = formData.currency,
                invoiceType = formData.invoiceType,
                status = "PENDING",
                issueDate = issueDateString,
                dueDate = formData.dueDate,
                billingAccountId = billingAccountId
            )


            addInvoiceToBillingAccountUseCase(billingAccountId, newInvoice)
                .onSuccess {

                    onShowAddInvoiceDialog(false)
                    loadAccountDetails()
                    _uiState.update {
                        it.copy(snackbarMessage = SnackbarMessage(message = LocalizedString.Resource(R.string.billing_add_invoice_success)))
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(snackbarMessage = SnackbarMessage(
                            message = LocalizedString.Resource(R.string.billing_add_invoice_error),
                            type = SnackbarType.ERROR
                        ))
                    }
                }
        }
    }
    fun loadAccountDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            getBillingAccountByIdUseCase(billingAccountId)
                .onSuccess { account ->
                    _uiState.update {
                        it.copy(isLoading = false, billingAccount = account)
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message,
                            snackbarMessage = SnackbarMessage(
                                message = LocalizedString.Resource(R.string.billing_details_error_message),
                                type = SnackbarType.ERROR
                            )
                        )
                    }
                }
        }
    }

    fun deleteInvoice(invoiceId: String) {
        val invoiceToDelete = _uiState.value.billingAccount?.invoices?.find { it.id == invoiceId }


        if (invoiceToDelete?.status?.uppercase() == "PAID") {
            _uiState.update {
                it.copy(snackbarMessage = SnackbarMessage(
                    message = LocalizedString.Resource(R.string.invoices_delete_error),
                    type = SnackbarType.ERROR
                ))
            }
            return
        }

        viewModelScope.launch {
            deleteInvoiceUseCase(billingAccountId, invoiceId)
                .onSuccess {
                    loadAccountDetails()
                    _uiState.update {
                        it.copy(snackbarMessage = SnackbarMessage(
                            message = LocalizedString.Resource(R.string.invoices_delete_success)
                        ))
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(snackbarMessage = SnackbarMessage(
                            message = LocalizedString.Resource(R.string.invoices_delete_error),
                            type = SnackbarType.ERROR
                        ))
                    }
                }
        }
    }

    fun markInvoiceAsPaid(invoiceId: String) {
        viewModelScope.launch {
            markInvoiceAsPaidUseCase(billingAccountId, invoiceId)
                .onSuccess {
                    loadAccountDetails()
                    _uiState.update {
                        it.copy(snackbarMessage = SnackbarMessage(
                            message = LocalizedString.Resource(R.string.invoices_mark_as_paid_success))
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(snackbarMessage = SnackbarMessage(
                            message = LocalizedString.Resource(R.string.invoices_mark_as_paid_error),
                            type = SnackbarType.ERROR
                        ))
                    }
                }
        }
    }

    fun clearSnackbarMessage() {
        _uiState.update { it.copy(snackbarMessage = null) }
    }

}
