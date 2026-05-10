# language: es
# Bounded Context: Enrollments
# Epic: EP002 - Gestión de Matrículas
# User Stories: US007, US008, US009

Característica: Gestión de Matrículas
  Como administrador
  Quiero gestionar las inscripciones en la plataforma
  Para asegurar que los usuarios estén correctamente registrados en los cursos

  Antecedentes:
    Dado que el administrador ha iniciado sesión en la plataforma
    Y que existen estudiantes, periodos y horarios disponibles en el sistema

  # ─────────────────────────────────────────────
  # US007 - Registro de Inscripción
  # ─────────────────────────────────────────────

  Esquema del escenario: US007 - Escenario 1 - Registro exitoso de inscripción
    Dado que el administrador tiene permisos de gestión de matrículas
    Cuando completa el formulario con los datos válidos de la inscripción
      | studentId | periodId | scheduleId | amount | currency | paymentStatus |
      | 1         | 1        | 1          | 350.00 | PEN      | PENDING       |
    Y hace clic en el botón "Registrar Matrícula"
    Entonces el sistema registra la inscripción correctamente
    Y el formulario se limpia exitosamente
    Y el estado "isFormSuccess" es verdadero

  Esquema del escenario: US007 - Escenario 2 - Error en el registro por datos incompletos
    Dado que el administrador tiene permisos de gestión de matrículas
    Cuando intenta guardar la inscripción sin completar los campos obligatorios
      | studentId | periodId | scheduleId | amount | currency | paymentStatus |
      | null      | null     | null       |        | PEN      |               |
    Entonces el botón "Registrar Matrícula" permanece deshabilitado
    Y el sistema no envía ninguna solicitud de registro

  Escenario: US007 - Escenario 3 - Error del servidor al registrar inscripción duplicada
    Dado que el administrador tiene permisos de gestión de matrículas
    Y el servidor responde con un error de conflicto al crear la matrícula
    Cuando completa el formulario con datos de un estudiante ya inscrito
      | studentId | periodId | scheduleId | amount | currency | paymentStatus |
      | 1         | 1        | 1          | 350.00 | PEN      | PENDING       |
    Y hace clic en el botón "Registrar Matrícula"
    Entonces el sistema muestra un mensaje de error al administrador
    Y el estado "isLoading" es falso

  # ─────────────────────────────────────────────
  # US008 - Actualización de Inscripción
  # ─────────────────────────────────────────────

  Escenario: US008 - Escenario 1 - Actualización exitosa de inscripción
    Dado que el administrador tiene permisos de gestión de matrículas
    Y existe una inscripción previamente registrada con id 10
    Cuando selecciona la inscripción para editar
    Y modifica los campos de la inscripción con información válida
      | amount | currency | paymentStatus | enrollmentStatus |
      | 400.00 | PEN      | PAID          | ACTIVE           |
    Y hace clic en el botón "Guardar Cambios"
    Entonces el sistema actualiza la inscripción correctamente
    Y el estado "isFormSuccess" es verdadero
    Y la lista de matrículas se recarga

  Escenario: US008 - Escenario 2 - Error al actualizar con datos inválidos
    Dado que el administrador tiene permisos de gestión de matrículas
    Y existe una inscripción previamente registrada con id 10
    Cuando selecciona la inscripción para editar
    Y el servidor responde con un error al intentar actualizar
    Y hace clic en el botón "Guardar Cambios"
    Entonces el sistema muestra un mensaje de error al administrador
    Y el estado "isLoading" es falso
    Y la inscripción no es modificada

  Escenario: US008 - Escenario 3 - Cancelación de la edición
    Dado que el administrador tiene permisos de gestión de matrículas
    Y existe una inscripción previamente registrada con id 10
    Cuando selecciona la inscripción para editar
    Y hace clic en el botón "Cancelar"
    Entonces el formulario se limpia exitosamente
    Y el campo "enrollmentToEdit" queda en nulo
    Y no se realiza ninguna llamada al servidor

  # ─────────────────────────────────────────────
  # US009 - Cancelación de Inscripción
  # ─────────────────────────────────────────────

  Escenario: US009 - Escenario 1 - Eliminación exitosa de inscripción
    Dado que el administrador tiene permisos de gestión de matrículas
    Y existe una inscripción activa con id 5
    Cuando hace clic en el botón eliminar de esa inscripción
    Y confirma la acción de eliminación
    Entonces el sistema elimina la inscripción correctamente
    Y la lista de matrículas se recarga sin la inscripción eliminada

  Escenario: US009 - Escenario 2 - Error del servidor al eliminar inscripción
    Dado que el administrador tiene permisos de gestión de matrículas
    Y existe una inscripción activa con id 5
    Y el servidor responde con un error al intentar eliminar
    Cuando hace clic en el botón eliminar de esa inscripción
    Entonces el sistema muestra un mensaje de error al administrador
    Y el estado "isLoading" es falso
    Y la lista de matrículas no se modifica
