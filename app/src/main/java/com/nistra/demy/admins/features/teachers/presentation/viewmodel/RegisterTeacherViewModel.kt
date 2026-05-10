package com.nistra.demy.admins.features.teachers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.common.LocalizedString
import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.core.common.SnackbarType
import com.nistra.demy.admins.features.teachers.domain.model.Teacher
import com.nistra.demy.admins.features.teachers.domain.usecase.GetTeachersUseCase
import com.nistra.demy.admins.features.teachers.domain.usecase.RegisterTeacherUseCase
import com.nistra.demy.admins.features.teachers.presentation.model.TeacherFormData
import com.nistra.demy.admins.features.teachers.presentation.state.RegisterTeacherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterTeacherViewModel @Inject constructor(
    private val registerTeacherUseCase: RegisterTeacherUseCase,
    private val getTeachersUseCase: GetTeachersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterTeacherUiState())
    val uiState: StateFlow<RegisterTeacherUiState> = _uiState.asStateFlow()

    private val _formData = MutableStateFlow(TeacherFormData())
    val formData: StateFlow<TeacherFormData> = _formData.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadTeachers()
    }

    fun onFieldChange(updated: TeacherFormData) {
        _formData.value = updated
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        filterTeachers(query)
    }

    private fun filterTeachers(query: String) {
        val allTeachers = _uiState.value.teachers
        val filtered = if (query.isBlank()) {
            allTeachers
        } else {
            allTeachers.filter { teacher ->
                teacher.firstName.contains(query, ignoreCase = true) ||
                teacher.lastName.contains(query, ignoreCase = true) ||
                teacher.email.contains(query, ignoreCase = true)
            }
        }
        _uiState.update { it.copy(filteredTeachers = filtered) }
    }

    fun loadTeachers() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingTeachers = true) }

            getTeachersUseCase()
                .onSuccess { teachers ->
                    _uiState.update {
                        it.copy(
                            teachers = teachers,
                            filteredTeachers = teachers,
                            isLoadingTeachers = false
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoadingTeachers = false,
                            snackbarMessage = SnackbarMessage(
                                message = error.message?.let { msg ->
                                    LocalizedString.Raw(msg)
                                } ?: LocalizedString.Resource(R.string.teachers_error_load_failed),
                                type = SnackbarType.ERROR,
                                actionLabel = LocalizedString.Resource(R.string.action_ok)
                            )
                        )
                    }
                }
        }
    }

    fun registerTeacher() {
        val data = _formData.value

        if (data.firstName.isBlank() || data.lastName.isBlank() || data.email.isBlank()) {
            _uiState.update {
                it.copy(
                    snackbarMessage = SnackbarMessage(
                        message = LocalizedString.Resource(R.string.teachers_validation_required_fields),
                        type = SnackbarType.WARNING,
                        actionLabel = LocalizedString.Resource(R.string.action_ok)
                    )
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, snackbarMessage = null) }

            val teacher = Teacher(
                id = "",
                firstName = data.firstName,
                lastName = data.lastName,
                email = data.email,
                countryCode = data.countryCode,
                phone = data.phone
            )

            registerTeacherUseCase(teacher)
                .onSuccess { registeredTeacher ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            snackbarMessage = SnackbarMessage(
                                message = LocalizedString.Resource(R.string.teachers_register_success),
                                type = SnackbarType.SUCCESS,
                                actionLabel = LocalizedString.Resource(R.string.action_ok)
                            )
                        )
                    }
                    _formData.value = TeacherFormData()
                    loadTeachers()
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            snackbarMessage = SnackbarMessage(
                                message = error.message?.let { msg ->
                                    LocalizedString.Raw(msg)
                                } ?: LocalizedString.Resource(R.string.teachers_error_register_failed),
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

