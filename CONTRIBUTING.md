# Collaboration Guide (Android)

Available languages:
- [English](CONTRIBUTING.md)
- [Spanish](CONTRIBUTING.es.md)

This repository contains the **Android mobile application** of the **Demy** project.  
This guide defines how we work as a team to maintain an organized and consistent workflow.

---

## 1) Quick Introduction

### Requirements
- **Android Studio** (latest stable)
- **JDK 21**
- **Android SDK 34+**
- Emulator or physical device running Android 8.0+

### Clone the repository
```
git clone <REPOSITORY-URL>
cd <REPO-NAME>
```

### Configure your Git user
```
git config --global user.name "Your Name"
git config --global user.email "your.email`example.com"
```

### Open the project
1. Open **Android Studio** → *Open* → select the repo folder.
2. Wait for Gradle sync.
3. Verify JDK in: *Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK = 21*.

### Build and run (Gradle Wrapper)
```
# Build
./gradlew clean build

# Unit tests (JVM)
./gradlew test

# Instrumented tests (requires emulator/device)
./gradlew connectedAndroidTest

# Lint/checks (if enabled in the project)
./gradlew lint ktlintCheck detekt
```

> Tip: If this is your first contribution, create a feature branch first (see *Git Workflow* section).

---

## 2) Git Workflow

- Central repository: **GitHub**. Main branch: `main`.
- Branching model: **Gitflow** (adapted for mobile).
    - `main`: stable releases (academic milestones).
    - `develop`: integration of new features.
    - Support branches:
        - `feature/<topic>` → new features.  
          Example: `feature/login-screen`, `feature/profile-edit`
        - `hotfix/<name>` → critical production fixes.  
          Example: `hotfix/fix-crash-on-rotate`
        - `release/vX.Y.Z` → release preparation.  
          Example: `release/v1.2.0`

### Keep your branch up to date (with `develop`)
```
# Be on your feature branch
git checkout feature/login-screen

# Fetch remote refs (without touching your working tree)
git fetch origin

# Merge changes from develop into your feature
git merge origin/develop

# If conflicts occur, resolve them, commit, then:
git push origin feature/login-screen
```

> Advantage: `git fetch` does not modify your working tree; you decide when to merge.

### Create your feature branch (from `develop`)
```
git checkout develop
git pull origin develop
git checkout -b feature/<your-topic>
```

---
## 3) Commit Messages (Conventional Commits)

We follow the **Conventional Commits** standard with an **optional scope** to indicate the affected area.

**Format**
```
<type>(<scope>): <message>
```

**Common types**
- `feat` → new functionality (screen/flow)
- `fix` → bug fix
- `refactor` → code improvement without behavior change
- `perf` → performance improvement (ANR, scrolling, rendering)
- `docs` → documentation (README, guides)
- `build` → Gradle/dependencies updates
- `ci` → pipelines and CI/CD
- `test` → unit/instrumented tests
- `style` → formatting (no logic changes)

**Suggested scopes (Android)**
- `feature_login`, `feature_home`, `feature_profile`
- `ui`, `navigation`, `core`, `data`, `room`, `network`, `i18n`, `a11y`, `build`

**Examples**
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

- **Base branch**: `develop` (never `main`).
- Use the **PR template** and explain **what** you changed and **why**.
- If applicable, link an Issue with `Closes #<issue-number>`.
- Request review from **at least one teammate**.
- Merge strategy: **Merge commit** (not squash) to keep full history.

### Checklist before opening a PR
- [ ] `feature/*` branch is synchronized with `origin/develop`.
- [ ] Project builds without errors: `./gradlew clean build`.
- [ ] Unit tests pass (if applicable): `./gradlew test`.
- [ ] Instrumented tests pass (if applicable): `./gradlew connectedAndroidTest`.
- [ ] Lint/checks pass (if enabled): `./gradlew lint ktlintCheck detekt`.
- [ ] UI changes manually tested on emulator/device (include screenshots/GIF if relevant).
- [ ] No secrets/keys/tokens committed.
- [ ] Commit messages follow **Conventional Commits**.
- [ ] Accessibility (a11y): basic labels/semantics verified if UI was modified.

---

## 5) Scope of Changes

- One **logical change per PR** (avoid giant PRs).
- Respect the organization: **`features/`** (each feature in its own package) and **`core/`** (themes, utils, navigation, shared resources).
- If adding local persistence, use **Room** (DAO, entities, migrations if required).
- For UI, follow **Material Design 3** and Compose best practices (state hoisting, useful previews, recomposition awareness).
- Always consider **i18n** (strings in `strings.xml`) and **a11y** (content descriptions, contrast, focus).

---

## 6) Code Style and Conventions

This project uses **Kotlin** and **Jetpack Compose**. We follow consistent style practices to keep the codebase clean, readable, and maintainable.

### General rules
- Always use **UTF-8** encoding.
- Maximum line length: **120 characters**.
- Indentation: **4 spaces** (no tabs).
- Each class should be in its own file with the same name as the class.
- Keep files small and cohesive; avoid “god classes.”
- Do not commit commented-out code.

### Kotlin conventions
- **Classes, Enums**: PascalCase (`UserProfile`, `LoginViewModel`).
- **Functions and variables**: camelCase (`loadUserData()`, `userName`).
- **Constants**: UPPERCASE with `_` (`MAX_RETRIES`, `DEFAULT_TIMEOUT`).
- **Packages**: lowercase, feature-based (`features.login`, `core.navigation`).
- Use `val` instead of `var` whenever possible.
- Prefer `data class` for simple models.
- Leverage Kotlin features: extension functions, sealed classes, coroutines, and Flow.

### Compose UI guidelines
- Use **state hoisting** and avoid unnecessary recompositions.
- Provide **`Preview** functions for major components/screens.
- Apply **Material Design 3** guidelines (theming, typography, spacing).
- Ensure **contentDescription** for images and icons (accessibility).
- Test layout responsiveness on multiple screen sizes (phones, tablets).

---

## 7) Testing Practices

- Preferably write **unit tests** for ViewModels, use cases, and repositories.
- If possible, write **instrumented tests** for Compose UI and navigation flows.
- Keep tests **fast and isolated**.
- Use **JUnit5**, **Robolectric**, and **Espresso / Compose UI Test** when applicable.

### Running tests
```
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest
```

### Guidelines
- One logical assertion per test.
- Use descriptive names: `fun loginFailsWithInvalidPassword()`.
- Use test doubles (mocks/fakes) where needed.
- Avoid flaky tests (synchronization issues, timing assumptions).

---

## 8) Best Practices

- One feature or bug fix per branch/PR.
- Keep PRs small and focused.
- Write **clear commit messages** (Conventional Commits).
- Ensure **i18n** (use `strings.xml`, no hardcoded text).
- Ensure **a11y** (labels, focus order, contrast).
- Run the app on at least **one emulator and one real device** before merging.
- Never commit secrets, API keys, or credentials.
- Document new or changed behavior in the PR description.

---

## 9) Conflict Resolution

- Regularly update your branch with `develop` to reduce conflicts:
  ```
  git checkout feature/<name>
  git fetch origin
  git merge origin/develop
  ```

- If conflicts occur:
    - Resolve them locally and commit.
    - For **critical files** (navigation setup, Gradle, core), coordinate with the team before resolving.
- Avoid force pushes unless absolutely necessary (`git push --force`).

---

## 10) Communication

- Use the agreed team channel (Discord/WhatsApp) for coordination.
- When opening a PR:
    - Describe **what** you changed and **why**.
    - Tag at least one teammate for review.
- Use GitHub Issues with the correct template (Bug, Feature, UI/UX, etc.).
- Keep communication clear, respectful, and professional (see [Code of Conduct](./CODE_OF_CONDUCT.md)).

---

## 11) Security Policy

Please do not report vulnerabilities publicly in Issues or PRs.  
See [SECURITY.md](./SECURITY.md) for details on how to report them privately.

---