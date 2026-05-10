package com.nistra.demy.admins.features.classrooms.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.classrooms.domain.usecase.CreateClassroomUseCase
import com.nistra.demy.admins.features.classrooms.domain.usecase.DeleteClassroomUseCase
import com.nistra.demy.admins.features.classrooms.domain.usecase.GetAllClassroomsUseCase
import com.nistra.demy.admins.features.classrooms.domain.usecase.UpdateClassroomUseCase
import com.nistra.demy.admins.features.classrooms.presentation.model.ClassroomFormData
import com.nistra.demy.admins.features.classrooms.presentation.state.ClassroomUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.update

@HiltViewModel
class ClassroomsViewModel @Inject constructor(
    private val getAllClassroomsUseCase: GetAllClassroomsUseCase,
    private val createClassroomUseCase: CreateClassroomUseCase,
    private val updateClassroomUseCase: UpdateClassroomUseCase,
    private val deleteClassroomUseCase: DeleteClassroomUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ClassroomUiState())
    val uiState: StateFlow<ClassroomUiState> = _uiState.asStateFlow()

    private val _formData = MutableStateFlow(ClassroomFormData())
    val formData: StateFlow<ClassroomFormData> = _formData.asStateFlow()

    private var allClassroomsCache: List<Classroom> = emptyList()

    init {
        loadClassrooms()
    }

    // UI Events (Métodos de interacción con la interfaz)

    fun onClassroomFormChange(updated: ClassroomFormData) {
        _formData.value = updated
        _uiState.update { it.copy(isFormSuccess = false, errorMessage = null) }
    }

    fun onSaveClassroomClick() {
        handleSaveClassroom()
    }

    fun onClassroomSelectedForEdit(classroom: Classroom) {
        _uiState.update { it.copy(classroomToEdit = classroom, errorMessage = null) }

        _formData.value = ClassroomFormData(
            code = classroom.code,
            capacityText = classroom.capacity.toString(),
            campus = classroom.campus
        )
    }

    fun onClearFormClick() {
        _uiState.update { it.copy(classroomToEdit = null, isFormSuccess = false, errorMessage = null) }
        _formData.value = ClassroomFormData()
    }

    fun onDeleteClassroomClick(classroom: Classroom) {
        handleDeleteClassroom(classroom)
    }

    fun searchClassrooms(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        val filteredList = filterClassrooms(allClassroomsCache, query)
        _uiState.update { it.copy(classrooms = filteredList) }
    }

    // Business Logic

    private fun loadClassrooms() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            getAllClassroomsUseCase()
                .onSuccess { fetchedClassrooms ->
                    allClassroomsCache = fetchedClassrooms
                    val filteredList = filterClassrooms(allClassroomsCache, _uiState.value.searchQuery)
                    _uiState.update { it.copy(classrooms = filteredList, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al cargar aulas.") }
                }
        }
    }

    private fun handleSaveClassroom() {
        val existingId = _uiState.value.classroomToEdit?.id ?: 0L
        val data = _formData.value

        val classroomToSave = Classroom(
            id = existingId,
            code = data.code.trim(),
            capacity = data.capacity,
            campus = data.campus.trim()
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = if (classroomToSave.id == 0L) {
                createClassroomUseCase(classroomToSave)
            } else {
                updateClassroomUseCase(classroomToSave)
            }

            result
                .onSuccess {
                    onClearFormClick()
                    loadClassrooms()
                    _uiState.update { it.copy(isFormSuccess = true, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al guardar el aula.") }
                }
        }
    }

    private fun handleDeleteClassroom(classroom: Classroom) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            deleteClassroomUseCase(classroom.id)
                .onSuccess {
                    loadClassrooms()
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al eliminar el aula.") }
                }
        }
    }

    private fun filterClassrooms(list: List<Classroom>, query: String): List<Classroom> {
        return if (query.isBlank()) {
            list
        } else {
            list.filter { classroom ->
                classroom.code.contains(query, ignoreCase = true) ||
                    classroom.campus.contains(query, ignoreCase = true)
            }
        }
    }
}
