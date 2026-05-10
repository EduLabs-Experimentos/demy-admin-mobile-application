# Guía de Colaboración (Android)

Idiomas disponibles:
- [Inglés](CONTRIBUTING.md)
- [Español](CONTRIBUTING.es.md)

Este repositorio contiene la **aplicación móvil Android** del proyecto **Demy**.  
Esta guía define cómo trabajamos en equipo para mantener un flujo organizado y consistente.

---

## 1) Introducción rápida

### Requisitos
- **Android Studio** (última versión estable)
- **JDK 21**
- **Android SDK 34+**
- Emulador o dispositivo físico con Android 8.0+

### Clonar el repositorio
```
git clone <REPOSITORY-URL>
cd <REPO-NAME>
```

### Configurar tu usuario de Git
```
git config --global user.name "Tu Nombre"
git config --global user.email "tu.email`ejemplo.com"
```

### Abrir el proyecto
1. Abre **Android Studio** → *Open* → selecciona la carpeta del repo.
2. Espera la sincronización de Gradle.
3. Verifica el JDK en: *Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK = 21*.

### Compilar y ejecutar (Gradle Wrapper)
```
# Compilar
./gradlew clean build

# Tests unitarios (JVM)
./gradlew test

# Tests instrumentados (requiere emulador/dispositivo)
./gradlew connectedAndroidTest

# Lint/checks (si están habilitados en el proyecto)
./gradlew lint ktlintCheck detekt
```

> Consejo: si es tu primera contribución, crea primero una rama de feature (ver sección *Workflow de Git*).

---

## 2) Workflow de Git

- Repositorio central: **GitHub**. Rama principal: `main`.
- Modelo de ramas: **Gitflow** (adaptado a mobile).
    - `main`: versiones estables (hitos académicos).
    - `develop`: integración de nuevas funcionalidades.
    - Ramas de soporte:
        - `feature/<tema>` → nuevas funcionalidades.  
          Ejemplo: `feature/login-screen`, `feature/profile-edit`
        - `hotfix/<nombre>` → correcciones críticas.  
          Ejemplo: `hotfix/fix-crash-on-rotate`
        - `release/vX.Y.Z` → preparación de versiones.  
          Ejemplo: `release/v1.2.0`

### Mantener tu rama actualizada (con `develop`)
```
# Estar en tu rama de trabajo
git checkout feature/login-screen

# Traer referencias remotas (sin tocar tu árbol de trabajo)
git fetch origin

# Integrar cambios de develop en tu feature
git merge origin/develop

# Si hubo conflictos, resolverlos, commit y luego:
git push origin feature/login-screen
```

> Ventaja: `git fetch` no modifica tu árbol de trabajo; tú decides cuándo hacer el merge.

### Crear tu rama de feature (desde `develop`)
```
git checkout develop
git pull origin develop
git checkout -b feature/<tu-tema>
```

---

## 3) Mensajes de commit (Conventional Commits)

Usamos el estándar de **Conventional Commits** en inglés, con un **scope opcional** para indicar el área afectada.

**Formato**
```
<type>(<scope>): <mensaje>
```

**Tipos comunes**
- `feat` → nueva funcionalidad (pantalla/flujo)
- `fix` → corrección de bug
- `refactor` → mejora de código sin cambiar el comportamiento
- `perf` → mejoras de rendimiento (ANR, scroll, renderizado)
- `docs` → documentación (README, guías)
- `build` → actualizaciones de Gradle/dependencias
- `ci` → pipelines y CI/CD
- `test` → tests unitarios/instrumentados
- `style` → formato (sin cambios lógicos)

**Scopes sugeridos (Android)**
- `feature_login`, `feature_home`, `feature_profile`
- `ui`, `navigation`, `core`, `data`, `room`, `network`, `i18n`, `a11y`, `build`

**Ejemplos**
```
feat(feature_login): add email/password sign-in screen
fix(ui): prevent crash on device rotation in HomeScreen
refactor(core): extract Result<T> helpers to common module
perf(feature_home): reduce recompositions on feed list
docs(readme): add setup and run instructions
build(gradle): update Kotlin and AGP versions
```

---

## 4) Pull Requests (PR)

- **Rama base**: `develop` (nunca `main`).
- Usa la **plantilla de PR** y explica **qué** cambiaste y **por qué**.
- Si aplica, vincula un Issue con `Closes #<número-de-issue>`.
- Solicita la revisión de **al menos un compañero**.
- Estrategia de merge: **Merge commit** (no squash) para mantener el historial completo.

### Checklist antes de abrir un PR
- [ ] La rama `feature/*` está sincronizada con `origin/develop`.
- [ ] El proyecto compila sin errores: `./gradlew clean build`.
- [ ] Los tests unitarios pasan (si aplica): `./gradlew test`.
- [ ] Los tests instrumentados pasan (si aplica): `./gradlew connectedAndroidTest`.
- [ ] Los chequeos de lint pasan (si están habilitados): `./gradlew lint ktlintCheck detekt`.
- [ ] Los cambios de UI fueron probados manualmente en emulador/dispositivo (incluir capturas/GIF si corresponde).
- [ ] No se han cometido secretos, llaves o tokens.
- [ ] Los mensajes de commit siguen **Conventional Commits**.
- [ ] Accesibilidad (a11y): verificados labels/semántica básica si se tocó la UI.

---

## 5) Alcance de cambios

- Un **cambio lógico por PR** (evita PRs gigantes).
- Respeta la organización: **`features/`** (cada feature en su propio paquete) y **`core/`** (temas, utilidades, navegación, recursos compartidos).
- Si agregas persistencia local, usa **Room** (DAO, entidades, migraciones si corresponde).
- Para la UI, sigue **Material Design 3** y buenas prácticas de Compose (state hoisting, previews útiles, cuidado con recompositions).
- Considera siempre **i18n** (usar `strings.xml`, no texto hardcodeado) y **a11y** (content descriptions, contraste, foco).

---

## 6) Estilo de código y convenciones

Este proyecto usa **Kotlin** y **Jetpack Compose**. Mantenemos reglas de estilo consistentes para asegurar un código limpio, legible y mantenible.

### Reglas generales
- Siempre usar codificación **UTF-8**.
- Longitud máxima de línea: **120 caracteres**.
- Indentación: **4 espacios** (no tabs).
- Cada clase debe estar en su propio archivo con el mismo nombre.
- Mantén archivos pequeños y cohesivos; evita “god classes”.
- No cometer código comentado innecesario.

### Convenciones de Kotlin
- **Clases y Enums**: PascalCase (`UserProfile`, `LoginViewModel`).
- **Funciones y variables**: camelCase (`loadUserData()`, `userName`).
- **Constantes**: UPPERCASE con `_` (`MAX_RETRIES`, `DEFAULT_TIMEOUT`).
- **Paquetes**: siempre en minúscula, basados en features (`features.login`, `core.navigation`).
- Usar `val` en lugar de `var` siempre que sea posible.
- Preferir `data class` para modelos simples.
- Usar las ventajas de Kotlin: extension functions, sealed classes, coroutines, Flow.

### Guías para Compose UI
- Usar **state hoisting** y evitar recompositions innecesarias.
- Incluir **`Preview** en componentes/pantallas principales.
- Aplicar lineamientos de **Material Design 3** (temas, tipografía, espaciado).
- Asegurar **contentDescription** en imágenes e íconos (accesibilidad).
- Probar la UI en múltiples tamaños de pantalla (teléfonos, tablets).

---

## 7) Prácticas de testing

- De preferencia escribir **tests unitarios** para ViewModels, casos de uso y repositorios.
- Si es posible, escribir **tests instrumentados** para Compose UI y flujos de navegación.
- Mantener los tests **rápidos y aislados**.
- Usar **JUnit5**, **Robolectric** y **Espresso / Compose UI Test** cuando aplique.

### Ejecutar tests
```
# Tests unitarios
./gradlew test

# Tests instrumentados
./gradlew connectedAndroidTest
```

### Guías
- Una aserción lógica por test.
- Nombres descriptivos: `fun loginFailsWithInvalidPassword()`.
- Usar test doubles (mocks/fakes) cuando sea necesario.
- Evitar tests frágiles (problemas de sincronización, dependencias de tiempo).

---

## 8) Buenas prácticas

- Una feature o bug fix por rama/PR.
- Mantén los PRs pequeños y enfocados.
- Escribe **mensajes de commit claros** (Conventional Commits).
- Asegura **i18n** (usar `strings.xml`, nada hardcodeado).
- Asegura **a11y** (labels, orden de foco, contraste).
- Corre la app en **al menos un emulador y un dispositivo real** antes de mergear.
- Nunca cometer secretos, llaves de API o credenciales.
- Documentar en el PR el nuevo comportamiento o cambios relevantes.

---

## 9) Resolución de conflictos

- Actualiza regularmente tu rama con `develop` para reducir conflictos:  
  ```
  git checkout feature/<nombre>
  git fetch origin
  git merge origin/develop
  ```

- Si ocurren conflictos:
    - Resuélvelos localmente y haz commit.
    - Para archivos **críticos** (setup de navegación, Gradle, core), coordina con el equipo antes de resolver.
- Evita los force pushes a menos que sea absolutamente necesario (`git push --force`).

---

## 10) Comunicación

- Usa el canal de equipo acordado (Discord/WhatsApp/Slack) para coordinar.
- Al abrir un PR:
    - Describe **qué** cambiaste y **por qué**.
    - Etiqueta al menos a un compañero para revisión.
- Usa GitHub Issues con la plantilla correcta (Bug, Feature, UI/UX, etc.).
- Mantén la comunicación clara, respetuosa y profesional (ver [Código de Conducta](./CODE_OF_CONDUCT.es.md)).

---

## 11) Política de Seguridad

Por favor, no reportes vulnerabilidades públicamente en Issues o Pull Requests.
Véase [SECURITY.md](./SECURITY.es.md) para detalles de cómo reportarlas de forma privada.

---