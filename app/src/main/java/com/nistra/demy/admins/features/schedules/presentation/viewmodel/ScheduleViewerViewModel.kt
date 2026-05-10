package com.nistra.demy.admins.features.schedules.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.usecase.GetAllSchedulesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewerViewModel @Inject constructor(
    private val getAllSchedulesUseCase: GetAllSchedulesUseCase
) : ViewModel() {

    private val _allSchedules = MutableStateFlow<List<Schedule>>(emptyList())
    val allSchedules: StateFlow<List<Schedule>> = _allSchedules.asStateFlow()

    private val _selectedSchedule = MutableStateFlow<Schedule?>(null)
    val selectedSchedule: StateFlow<Schedule?> = _selectedSchedule.asStateFlow()

    private val _selectedScheduleName = MutableStateFlow<String>("")
    val selectedScheduleName: StateFlow<String> = _selectedScheduleName.asStateFlow()

    init {
        loadAllSchedules()
    }

    fun loadAllSchedules() {
        viewModelScope.launch {
            getAllSchedulesUseCase().onSuccess { schedules ->
                _allSchedules.value = schedules

                schedules.firstOrNull()?.let { firstSchedule ->
                    selectSchedule(firstSchedule.id, firstSchedule.name)
                }
            }
        }
    }

    fun selectSchedule(id: Long, name: String) {
        val schedule = _allSchedules.value.find { it.id == id }
        _selectedSchedule.value = schedule
        _selectedScheduleName.value = name
    }
}
