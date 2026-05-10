package com.nistra.demy.admins.features.finance.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.common.LocalizedString
import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.core.common.SnackbarType
import com.nistra.demy.admins.features.finance.domain.model.Transaction
import com.nistra.demy.admins.features.finance.domain.usecase.GetAllTransactionsUseCase
import com.nistra.demy.admins.features.finance.domain.usecase.GetTransactionByIdUseCase
import com.nistra.demy.admins.features.finance.domain.usecase.RegisterTransactionUseCase
import com.nistra.demy.admins.features.finance.presentation.model.ChartData
import com.nistra.demy.admins.features.finance.presentation.model.ChartDataProcessor
import com.nistra.demy.admins.features.finance.presentation.model.TransactionFormData
import com.nistra.demy.admins.features.finance.presentation.state.RegisterTransactionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterTransactionViewModel @Inject constructor(
    private val registerTransactionUseCase: RegisterTransactionUseCase,
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    @Suppress("unused") private val getTransactionByIdUseCase: GetTransactionByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterTransactionUiState())
    val uiState: StateFlow<RegisterTransactionUiState> = _uiState.asStateFlow()

    private val _formData = MutableStateFlow(TransactionFormData())
    val formData: StateFlow<TransactionFormData> = _formData.asStateFlow()

    private val _chartData = MutableStateFlow<ChartData?>(null)
    val chartData: StateFlow<ChartData?> = _chartData.asStateFlow()

    init {
        loadTransactions()
    }

    fun onFieldChange(updated: TransactionFormData) {
        _formData.value = updated
    }

    fun loadTransactions() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingTransactions = true) }

            getAllTransactionsUseCase()
                .onSuccess { transactions ->
                    val processedChartData = ChartDataProcessor.processTransactions(transactions)
                    _uiState.update {
                        it.copy(
                            transactions = transactions,
                            filteredTransactions = transactions,
                            isLoadingTransactions = false
                        )
                    }
                    _chartData.value = processedChartData
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoadingTransactions = false,
                            snackbarMessage = SnackbarMessage(
                                message = error.message?.let { msg ->
                                    LocalizedString.Raw(msg)
                                } ?: LocalizedString.Resource(R.string.finance_error_load_failed),
                                type = SnackbarType.ERROR,
                                actionLabel = LocalizedString.Resource(R.string.action_ok)
                            )
                        )
                    }
                }
        }
    }

    fun registerTransaction() {
        val data = _formData.value

        if (data.type == null || data.category == null || data.method == null ||
            data.amount.isBlank() || data.date.isBlank()) {
            _uiState.update {
                it.copy(
                    snackbarMessage = SnackbarMessage(
                        message = LocalizedString.Resource(R.string.finance_validation_required_fields),
                        type = SnackbarType.WARNING,
                        actionLabel = LocalizedString.Resource(R.string.action_ok)
                    )
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, snackbarMessage = null) }

            val transaction = Transaction(
                id = "",
                type = data.type.value,
                category = data.category.value,
                method = data.method.value,
                amount = data.amount,
                currency = data.currency.code,
                description = data.description,
                date = data.date
            )

            registerTransactionUseCase(transaction)
                .onSuccess { _ ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            snackbarMessage = SnackbarMessage(
                                message = LocalizedString.Resource(R.string.finance_register_success),
                                type = SnackbarType.SUCCESS,
                                actionLabel = LocalizedString.Resource(R.string.action_ok)
                            )
                        )
                    }
                    _formData.value = TransactionFormData()
                    loadTransactions()
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            snackbarMessage = SnackbarMessage(
                                message = error.message?.let { msg ->
                                    LocalizedString.Raw(msg)
                                } ?: LocalizedString.Resource(R.string.finance_error_register_failed),
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
