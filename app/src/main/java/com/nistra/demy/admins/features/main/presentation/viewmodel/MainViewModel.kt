package com.nistra.demy.admins.features.main.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.nistra.demy.admins.core.storage.SessionPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val sessionPreferences: SessionPreferences
) : ViewModel()
