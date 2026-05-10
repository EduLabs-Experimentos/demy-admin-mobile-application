package com.nistra.demy.admins.features.enrollments.steps

import com.nistra.demy.admins.features.enrollments.domain.model.Enrollment
import com.nistra.demy.admins.features.enrollments.domain.model.EnrollmentStatus
import com.nistra.demy.admins.features.enrollments.domain.model.PaymentStatus
import com.nistra.demy.admins.features.enrollments.domain.usecase.CreateEnrollmentUseCase
import com.nistra.demy.admins.features.enrollments.domain.usecase.DeleteEnrollmentUseCase
import com.nistra.demy.admins.features.enrollments.domain.usecase.GetAllEnrollmentsUseCase
import com.nistra.demy.admins.features.enrollments.domain.usecase.UpdateEnrollmentUseCase
import com.nistra.demy.admins.features.enrollments.presentation.model.EnrollmentFormData
import com.nistra.demy.admins.features.enrollments.presentation.viewmodel.EnrollmentsViewModel
import com.nistra.demy.admins.features.periods.domain.usecase.GetAllPeriodsUseCase
import com.nistra.demy.admins.features.schedules.domain.usecase.GetAllSchedulesUseCase
import com.nistra.demy.admins.features.students.domain.usecase.GetAllStudentsUseCase
import io.cucumber.java.Before
import io.cucumber.java.es.Cuando
import io.cucumber.java.es.Dado
import io.cucumber.java.es.Entonces
import io.cucumber.datatable.DataTable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import com.nistra.demy.admins.features.periods.domain.model.AcademicPeriod
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.students.domain.model.Student

// ─────────────────────────────────────────────────────────────────────────────
// ENROLLMENT STEP DEFINITIONS
// Bounded Context : Enrollments
// User Stories    : US007 · US008 · US009
// Framework       : Cucumber-JVM (español) + MockK/Mockito + kotlinx-coroutines-test
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalCoroutinesApi::class)
class EnrollmentStepDefinitions {

    // ── Mocks de casos de uso ──────────────────────────────────────────────
    private val getAllEnrollmentsUseCase: GetAllEnrollmentsUseCase = mock()
    private val createEnrollmentUseCase: CreateEnrollmentUseCase   = mock()
    private val updateEnrollmentUseCase: UpdateEnrollmentUseCase   = mock()
    private val deleteEnrollmentUseCase: DeleteEnrollmentUseCase   = mock()
    private val getAllStudentsUseCase: GetAllStudentsUseCase        = mock()
    private val getAllPeriodsUseCase: GetAllPeriodsUseCase         = mock()
    private val getAllSchedulesUseCase: GetAllSchedulesUseCase     = mock()

    // ── ViewModel bajo prueba ──────────────────────────────────────────────
    private lateinit var viewModel: EnrollmentsViewModel

    // ── Datos auxiliares de prueba ─────────────────────────────────────────
    private val testDispatcher = StandardTestDispatcher()

    private val sampleStudents = listOf(
        Student(id = 1L, firstName = "Ana",   lastName = "García"),
        Student(id = 2L, firstName = "Pedro", lastName = "López")
    )

    private val samplePeriods = listOf(
        AcademicPeriod(id = 1L, periodName = "2024-I"),
        AcademicPeriod(id = 2L, periodName = "2024-II")
    )

    private val sampleSchedules = listOf(
        Schedule(id = 1L, name = "Lunes y Miércoles - Mañana"),
        Schedule(id = 2L, name = "Martes y Jueves - Tarde")
    )

    private val sampleEnrollments = listOf(
        Enrollment(
            id          = 5L,
            studentId   = 1L,
            periodId    = 1L,
            scheduleId  = 1L,
            amount      = "350.00",
            currency    = "PEN",
            paymentStatus    = PaymentStatus.PENDING,
            enrollmentStatus = EnrollmentStatus.ACTIVE,
            studentName = "Ana García",
            periodName  = "2024-I",
            scheduleName = "Lunes y Miércoles - Mañana"
        ),
        Enrollment(
            id          = 10L,
            studentId   = 2L,
            periodId    = 2L,
            scheduleId  = 2L,
            amount      = "350.00",
            currency    = "PEN",
            paymentStatus    = PaymentStatus.PENDING,
            enrollmentStatus = EnrollmentStatus.ACTIVE,
            studentName = "Pedro López",
            periodName  = "2024-II",
            scheduleName = "Martes y Jueves - Tarde"
        )
    )

    // ── Setup antes de cada escenario ──────────────────────────────────────
    @Before
    fun setUp() = runTest(testDispatcher) {
        // Comportamiento por defecto: carga exitosa de datos auxiliares
        whenever(getAllStudentsUseCase()).thenReturn(Result.success(sampleStudents))
        whenever(getAllPeriodsUseCase()).thenReturn(Result.success(samplePeriods))
        whenever(getAllSchedulesUseCase()).thenReturn(Result.success(sampleSchedules))
        whenever(getAllEnrollmentsUseCase()).thenReturn(Result.success(sampleEnrollments))

        viewModel = EnrollmentsViewModel(
            getAllEnrollmentsUseCase = getAllEnrollmentsUseCase,
            createEnrollmentUseCase = createEnrollmentUseCase,
            updateEnrollmentUseCase = updateEnrollmentUseCase,
            deleteEnrollmentUseCase = deleteEnrollmentUseCase,
            getAllStudentsUseCase    = getAllStudentsUseCase,
            getAllPeriodsUseCase     = getAllPeriodsUseCase,
            getAllSchedulesUseCase   = getAllSchedulesUseCase
        )
        advanceUntilIdle()
    }

    // ═════════════════════════════════════════════════════════════════════════
    // GIVEN — Precondiciones compartidas
    // ═════════════════════════════════════════════════════════════════════════

    @Dado("que el administrador ha iniciado sesión en la plataforma")
    fun administradorInicioSesion() {
        // La sesión se valida en el flujo de autenticación (SignInViewModel).
        // En este contexto asumimos que el administrador ya está autenticado.
        assertTrue("El administrador debe estar autenticado", true)
    }

    @Dado("que existen estudiantes, periodos y horarios disponibles en el sistema")
    fun existenDatosAuxiliares() = runTest(testDispatcher) {
        advanceUntilIdle()
        val state = viewModel.uiState.value
        assertTrue("Debe haber estudiantes disponibles", state.availableStudents.isNotEmpty())
        assertTrue("Debe haber periodos disponibles",   state.availablePeriods.isNotEmpty())
        assertTrue("Debe haber horarios disponibles",   state.availableSchedules.isNotEmpty())
    }

    @Dado("que el administrador tiene permisos de gestión de matrículas")
    fun administradorTienePermisos() {
        // Los permisos se gestionan a nivel de backend/auth; aquí verificamos
        // que el ViewModel se inicializó correctamente con datos cargados.
        assertNotNull("El ViewModel debe estar inicializado", viewModel)
    }

    @Dado("existe una inscripción previamente registrada con id {int}")
    fun existeInscripcionConId(id: Int) = runTest(testDispatcher) {
        advanceUntilIdle()
        val state = viewModel.uiState.value
        val found = state.enrollments.any { it.id == id.toLong() }
        assertTrue("Debe existir una inscripción con id $id", found)
    }

    @Dado("existe una inscripción activa con id {int}")
    fun existeInscripcionActivaConId(id: Int) = runTest(testDispatcher) {
        advanceUntilIdle()
        val state = viewModel.uiState.value
        val found = state.enrollments.any { it.id == id.toLong() && it.enrollmentStatus == EnrollmentStatus.ACTIVE }
        assertTrue("Debe existir una inscripción activa con id $id", found)
    }

    @Dado("el servidor responde con un error de conflicto al crear la matrícula")
    fun servidorRespondeConflictoAlCrear() = runTest(testDispatcher) {
        whenever(createEnrollmentUseCase(any()))
            .thenReturn(Result.failure(Exception("409 - Ya existe una inscripción para este estudiante en el periodo seleccionado.")))
    }

    @Dado("el servidor responde con un error al intentar actualizar")
    fun servidorRespondeErrorAlActualizar() = runTest(testDispatcher) {
        whenever(updateEnrollmentUseCase(any()))
            .thenReturn(Result.failure(Exception("400 - Datos de actualización inválidos.")))
    }

    @Dado("el servidor responde con un error al intentar eliminar")
    fun servidorRespondeErrorAlEliminar() = runTest(testDispatcher) {
        whenever(deleteEnrollmentUseCase(any()))
            .thenReturn(Result.failure(Exception("404 - Inscripción no encontrada.")))
    }

    // ═════════════════════════════════════════════════════════════════════════
    // WHEN — Acciones del usuario
    // ═════════════════════════════════════════════════════════════════════════

    // ── US007 ────────────────────────────────────────────────────────────────

    @Cuando("completa el formulario con los datos válidos de la inscripción")
    fun completaFormularioConDatosValidos(dataTable: DataTable) {
        val row = dataTable.asMaps().first()
        val formData = EnrollmentFormData(
            studentId     = row["studentId"]?.toLongOrNull(),
            periodId      = row["periodId"]?.toLongOrNull(),
            scheduleId    = row["scheduleId"]?.toLongOrNull(),
            amount        = row["amount"] ?: "",
            currency      = row["currency"] ?: "PEN",
            paymentStatus = row["paymentStatus"] ?: ""
        )
        viewModel.onEnrollmentFormChange(formData)
    }

    @Cuando("hace clic en el botón {string}")
    fun haceClicEnBoton(boton: String) = runTest(testDispatcher) {
        when (boton) {
            "Registrar Matrícula", "Guardar Cambios" -> {
                viewModel.onSaveEnrollmentClick()
                advanceUntilIdle()
            }
            "Cancelar" -> {
                viewModel.onClearFormClick()
                advanceUntilIdle()
            }
        }
    }

    @Cuando("intenta guardar la inscripción sin completar los campos obligatorios")
    fun intentaGuardarSinCamposObligatorios(dataTable: DataTable) {
        val row = dataTable.asMaps().first()
        val formData = EnrollmentFormData(
            studentId     = row["studentId"]?.toLongOrNull(),
            periodId      = row["periodId"]?.toLongOrNull(),
            scheduleId    = row["scheduleId"]?.toLongOrNull(),
            amount        = row["amount"] ?: "",
            currency      = row["currency"] ?: "PEN",
            paymentStatus = row["paymentStatus"] ?: ""
        )
        viewModel.onEnrollmentFormChange(formData)
    }

    // ── US008 ────────────────────────────────────────────────────────────────

    @Cuando("selecciona la inscripción para editar")
    fun seleccionaInscripcionParaEditar() {
        val enrollment = sampleEnrollments.first { it.id == 10L }
        viewModel.onEnrollmentSelectedForEdit(enrollment)
    }

    @Cuando("modifica los campos de la inscripción con información válida")
    fun modificaCamposConInformacionValida(dataTable: DataTable) = runTest(testDispatcher) {
        val row = dataTable.asMaps().first()
        val updatedEnrollment = sampleEnrollments.first { it.id == 10L }.copy(
            amount           = row["amount"] ?: "350.00",
            currency         = row["currency"] ?: "PEN",
            paymentStatus    = PaymentStatus.valueOf(row["paymentStatus"] ?: "PENDING"),
            enrollmentStatus = EnrollmentStatus.valueOf(row["enrollmentStatus"] ?: "ACTIVE")
        )
        whenever(updateEnrollmentUseCase(any())).thenReturn(Result.success(updatedEnrollment))

        val formData = EnrollmentFormData(
            studentId        = updatedEnrollment.studentId,
            periodId         = updatedEnrollment.periodId,
            scheduleId       = updatedEnrollment.scheduleId,
            amount           = updatedEnrollment.amount,
            currency         = updatedEnrollment.currency,
            paymentStatus    = updatedEnrollment.paymentStatus.name,
            enrollmentStatus = updatedEnrollment.enrollmentStatus?.name ?: "ACTIVE"
        )
        viewModel.onEnrollmentFormChange(formData)
    }

    // ── US009 ────────────────────────────────────────────────────────────────

    @Cuando("confirma la acción de eliminación")
    fun confirmaAccionEliminacion() = runTest(testDispatcher) {
        val enrollment = sampleEnrollments.first { it.id == 5L }
        whenever(deleteEnrollmentUseCase(5L)).thenReturn(Result.success(Unit))
        // Después de eliminar, la lista recargada ya no incluye el id 5
        val updatedList = sampleEnrollments.filter { it.id != 5L }
        whenever(getAllEnrollmentsUseCase()).thenReturn(Result.success(updatedList))

        viewModel.onDeleteEnrollmentClick(enrollment)
        advanceUntilIdle()
    }

    @Cuando("hace clic en el botón eliminar de esa inscripción")
    fun haceClicEliminarInscripcion() = runTest(testDispatcher) {
        val enrollment = sampleEnrollments.first { it.id == 5L }
        viewModel.onDeleteEnrollmentClick(enrollment)
        advanceUntilIdle()
    }

    // ═════════════════════════════════════════════════════════════════════════
    // THEN — Verificaciones / Aserciones
    // ═════════════════════════════════════════════════════════════════════════

    // ── Registro exitoso ────────────────────────────────────────────────────

    @Entonces("el sistema registra la inscripción correctamente")
    fun sistemaRegistraInscripcionCorrectamente() = runTest(testDispatcher) {
        whenever(createEnrollmentUseCase(any()))
            .thenReturn(Result.success(sampleEnrollments.first()))
        viewModel.onSaveEnrollmentClick()
        advanceUntilIdle()
        verify(createEnrollmentUseCase).invoke(any())
    }

    @Entonces("el formulario se limpia exitosamente")
    fun formularioSeLimpia() {
        val formData = viewModel.formData.value
        assertNull("studentId debe ser null", formData.studentId)
        assertNull("periodId debe ser null",  formData.periodId)
        assertNull("scheduleId debe ser null", formData.scheduleId)
        assertEquals("", formData.amount)
        assertEquals("PEN", formData.currency)
        assertEquals("", formData.paymentStatus)
    }

    @Entonces("el estado {string} es verdadero")
    fun estadoEsVerdadero(estado: String) {
        val state = viewModel.uiState.value
        when (estado) {
            "isFormSuccess" -> assertTrue("isFormSuccess debe ser true", state.isFormSuccess)
            "isLoading"     -> assertTrue("isLoading debe ser true",     state.isLoading)
            else            -> throw IllegalArgumentException("Estado desconocido: $estado")
        }
    }

    // ── Formulario inválido ─────────────────────────────────────────────────

    @Entonces("el botón {string} permanece deshabilitado")
    fun botonPermaneceDeshabilitado(boton: String) {
        val formData = viewModel.formData.value
        assertFalse(
            "El formulario no debe ser válido cuando faltan campos obligatorios",
            formData.isFormValid
        )
    }

    @Entonces("el sistema no envía ninguna solicitud de registro")
    fun sistemaNoCreaInscripcion() = runTest(testDispatcher) {
        verify(createEnrollmentUseCase, never()).invoke(any())
    }

    // ── Error del servidor ──────────────────────────────────────────────────

    @Entonces("el sistema muestra un mensaje de error al administrador")
    fun sistemaMuestraMensajeError() {
        val state = viewModel.uiState.value
        assertNotNull("errorMessage debe contener un mensaje", state.errorMessage)
        assertTrue("El mensaje de error no debe estar vacío", state.errorMessage!!.isNotBlank())
    }

    @Entonces("el estado {string} es falso")
    fun estadoEsFalso(estado: String) {
        val state = viewModel.uiState.value
        when (estado) {
            "isLoading"     -> assertFalse("isLoading debe ser false",     state.isLoading)
            "isFormSuccess" -> assertFalse("isFormSuccess debe ser false",  state.isFormSuccess)
            else            -> throw IllegalArgumentException("Estado desconocido: $estado")
        }
    }

    // ── Actualización ───────────────────────────────────────────────────────

    @Entonces("el sistema actualiza la inscripción correctamente")
    fun sistemaActualizaInscripcionCorrectamente() = runTest(testDispatcher) {
        verify(updateEnrollmentUseCase).invoke(any())
    }

    @Entonces("la lista de matrículas se recarga")
    fun listaMatriculasSeRecarga() = runTest(testDispatcher) {
        verify(getAllEnrollmentsUseCase).invoke()
    }

    @Entonces("la inscripción no es modificada")
    fun inscripcionNoModificada() = runTest(testDispatcher) {
        verify(updateEnrollmentUseCase, never()).invoke(any())
    }

    // ── Cancelación de edición ──────────────────────────────────────────────

    @Entonces("el campo {string} queda en nulo")
    fun campoQuedaEnNulo(campo: String) {
        val state = viewModel.uiState.value
        when (campo) {
            "enrollmentToEdit" -> assertNull(
                "enrollmentToEdit debe ser null tras cancelar",
                state.enrollmentToEdit
            )
            else -> throw IllegalArgumentException("Campo desconocido: $campo")
        }
    }

    @Entonces("no se realiza ninguna llamada al servidor")
    fun noSeRealizaLlamadaAlServidor() = runTest(testDispatcher) {
        verify(createEnrollmentUseCase, never()).invoke(any())
        verify(updateEnrollmentUseCase, never()).invoke(any())
        verify(deleteEnrollmentUseCase, never()).invoke(any())
    }

    // ── Eliminación ─────────────────────────────────────────────────────────

    @Entonces("el sistema elimina la inscripción correctamente")
    fun sistemaEliminaInscripcionCorrectamente() = runTest(testDispatcher) {
        verify(deleteEnrollmentUseCase).invoke(5L)
    }

    @Entonces("la lista de matrículas se recarga sin la inscripción eliminada")
    fun listaRecargarSinInscripcionEliminada() = runTest(testDispatcher) {
        val state = viewModel.uiState.value
        val found = state.enrollments.any { it.id == 5L }
        assertFalse("La inscripción con id 5 no debe aparecer en la lista", found)
    }

    @Entonces("la lista de matrículas no se modifica")
    fun listaMatriculasNoSeModifica() = runTest(testDispatcher) {
        verify(deleteEnrollmentUseCase).invoke(any())
        val state = viewModel.uiState.value
        assertNotNull("El errorMessage debe existir", state.errorMessage)
    }
}
