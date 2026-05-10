package com.nistra.demy.admins.features.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.core.storage.SessionPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sessionPreferences: SessionPreferences
) : ViewModel() {

    private val _isSessionActive = MutableStateFlow<Boolean?>(null)
    val isSessionActive: StateFlow<Boolean?> = _isSessionActive

    init {
        viewModelScope.launch {
            val token = sessionPreferences.token.firstOrNull()
            _isSessionActive.value = !token.isNullOrEmpty()
        }
    }
}
