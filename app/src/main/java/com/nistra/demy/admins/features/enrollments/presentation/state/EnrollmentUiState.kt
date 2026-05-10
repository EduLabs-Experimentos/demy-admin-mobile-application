package com.nistra.demy.admins.features.enrollments.presentation.state

import com.nistra.demy.admins.features.enrollments.domain.model.Enrollment
import com.nistra.demy.admins.features.enrollments.presentation.model.EnrollmentFormData
import com.nistra.demy.admins.features.periods.domain.model.AcademicPeriod
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.students.domain.model.Student

data class EnrollmentUiState(
    // Lista principal de matrículas
    val enrollments: List<Enrollment> = emptyList(),
    val enrollmentToEdit: Enrollment? = null,

    // Búsqueda y carga
    val searchQuery: String = "",
    val isFormSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    // Datos auxiliares para dropdowns
    val availableStudents: List<Student> = emptyList(),
    val availablePeriods: List<AcademicPeriod> = emptyList(),
    val availableSchedules: List<Schedule> = emptyList(),

    // Estado del formulario
    val formData: EnrollmentFormData = EnrollmentFormData(),
    val isFormLoading: Boolean = false,
)
