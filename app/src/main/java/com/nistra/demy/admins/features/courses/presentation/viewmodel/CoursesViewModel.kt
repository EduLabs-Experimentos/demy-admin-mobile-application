package com.nistra.demy.admins.features.courses.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.courses.domain.usecase.CreateCourseUseCase
import com.nistra.demy.admins.features.courses.domain.usecase.DeleteCourseUseCase
import com.nistra.demy.admins.features.courses.domain.usecase.GetAllCoursesUseCase
import com.nistra.demy.admins.features.courses.domain.usecase.UpdateCourseUseCase
import com.nistra.demy.admins.features.courses.presentation.model.CourseFormData
import com.nistra.demy.admins.features.courses.presentation.state.CourseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val getAllCoursesUseCase: GetAllCoursesUseCase,
    private val createCourseUseCase: CreateCourseUseCase,
    private val updateCourseUseCase: UpdateCourseUseCase,
    private val deleteCourseUseCase: DeleteCourseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CourseUiState())
    val uiState: StateFlow<CourseUiState> = _uiState.asStateFlow()

    private val _formData = MutableStateFlow(CourseFormData())
    val formData: StateFlow<CourseFormData> = _formData.asStateFlow()

    private var allCoursesCache: List<Course> = emptyList()


    init {
        loadCourses()
    }

    fun onCourseFormChange(updated: CourseFormData) {
        _formData.value = updated
        _uiState.update { it.copy(isFormSuccess = false, errorMessage = null) }
    }

    fun onSaveCourseClick() {
        handleSaveCourse()
    }

    fun onCourseSelectedForEdit(course: Course) {
        _uiState.update { it.copy(courseToEdit = course, errorMessage = null) }
        _formData.value = CourseFormData(
            name = course.name,
            code = course.code,
            description = course.description
        )
    }

    fun onClearFormClick() {
        _uiState.update { it.copy(courseToEdit = null, isFormSuccess = false, errorMessage = null) }
        _formData.value = CourseFormData()
    }

    fun onDeleteCourseClick(course: Course) {
        handleDeleteCourse(course)
    }

    fun searchCourses(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        val filteredList = filterCourses(allCoursesCache, query)
        _uiState.update { it.copy(courses = filteredList) }
    }

    private fun loadCourses() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            getAllCoursesUseCase()
                .onSuccess { fetchedCourses ->
                    allCoursesCache = fetchedCourses
                    val filteredList = filterCourses(allCoursesCache, _uiState.value.searchQuery)
                    _uiState.update { it.copy(courses = filteredList, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al cargar cursos.") }
                }
        }
    }

    private fun handleSaveCourse() {
        val existingId = _uiState.value.courseToEdit?.id ?: 0L
        val data = _formData.value

        val courseToSave = Course(
            id = existingId,
            name = data.name.trim(),
            code = data.code.trim(),
            description = data.description.trim()
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = if (courseToSave.id == 0L) {
                createCourseUseCase(courseToSave)
            } else {
                updateCourseUseCase(courseToSave)
            }

            result
                .onSuccess {
                    onClearFormClick()
                    loadCourses()
                    _uiState.update { it.copy(isFormSuccess = true, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al guardar el curso.") }
                }
        }
    }

    private fun handleDeleteCourse(course: Course) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            deleteCourseUseCase(course.id)
                .onSuccess {
                    loadCourses()
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al eliminar el curso.") }
                }
        }
    }

    private fun filterCourses(list: List<Course>, query: String): List<Course> {
        return if (query.isBlank()) {
            list
        } else {
            list.filter { course ->
                course.name.contains(query, ignoreCase = true) ||
                    course.code.contains(query, ignoreCase = true)
            }
        }
    }
}
