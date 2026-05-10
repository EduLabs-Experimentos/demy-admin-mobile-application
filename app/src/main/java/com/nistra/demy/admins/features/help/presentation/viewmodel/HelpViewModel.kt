package com.nistra.demy.admins.features.help.presentation.viewmodel

import com.nistra.demy.admins.features.help.presentation.model.HelpUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HelpViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<HelpUiState>(HelpUiState.Loading)
    val uiState: StateFlow<HelpUiState> = _uiState.asStateFlow()

    init {
        loadHelpInfo()
    }
    private fun loadHelpInfo() = viewModelScope.launch {
        kotlinx.coroutines.delay(300)
        _uiState.value = HelpUiState.Success(
            email = "demy@contact.com",
            phone = "+51 999 999 999",
            schedule = "Mon–Fri, 9am–6pm"
        )
    }
}
