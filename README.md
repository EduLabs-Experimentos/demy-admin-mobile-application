# Demy Admins App – Android Mobile (Kotlin + Jetpack Compose)

This repository contains the **mobile application** of the **Demy** project, developed in **Kotlin** with **Jetpack Compose**.  
The goal is to provide a modern and accessible Android app for the **target segment of administrators**, aligned with the project’s backend and academic requirements.

---

## Features  

- **Modern UI with Jetpack Compose**
- **Persistence with Room** for local offline storage
- **Material Design 3** theming and components
- **Internationalization (i18n)** support for multiple languages
- **Accessibility (a11y)** with compliance to **WCAG** standards
- 100% **Kotlin** (no Java)

---

## Architecture – MVVM + Modular Design

The project follows **MVVM (Model-View-ViewModel)** combined with **feature-based modularization**.  
Currently, the app is built in a **single Gradle module**, but structured into **feature packages** and **core components**.

```
app/
├─ core/                 # Shared utilities, theme, navigation, resources
└─ features/
   ├─ login/             # Authentication flow
   ├─ home/              # Main administrator dashboard
   ├─ profile/           # Administrator profile and settings
   └─ ...
```

- **UI layer** → Jetpack Compose screens + ViewModels
- **Domain layer** → use cases and business logic
- **Data layer** → repositories, Room, and external API calls

---

## Installation / Setup  

### Prerequisites
- **Android Studio (latest stable)**
- **JDK 21**
- Android SDK 34+
- An emulator or physical device running Android 8.0+

### Run locally
```
# Clone repository
git clone https://github.com/<org>/<repo>.git
cd <repo>

# Build project
./gradlew clean build

# Install debug build on device/emulator
./gradlew installDebug
```

---

## Project structure

- `app/src/main/java` → application source code
- `app/src/androidTest/java` → instrumented tests
- `app/src/test/java` → unit tests
- `docs/` → extended documentation (guides, diagrams, design system)
- `CONTRIBUTING.md` → contribution rules (commits, branches, PRs, code style)
- `CODE_OF_CONDUCT.md` → community guidelines
- `SECURITY.md` → report and handle vulnerabilities
- `AUTHORS.md` → project contributors

---

## Contributing

We use GitHub **Issues** and **Pull Requests** to collaborate.  
Please use the provided templates when creating:
- Bug reports
- Feature requests
- UI/UX issues
- Performance issues
- Refactors / technical debt
- Documentation improvements
- Generic issues

See [CONTRIBUTING.md](./CONTRIBUTING.md) for details.  
Please also review our [Code of Conduct](./CODE_OF_CONDUCT.md).

---

## Issue Templates

Available in [`.github/ISSUE_TEMPLATE/`](./.github/ISSUE_TEMPLATE/):

- Bug Report
- Feature Request
- UI/UX Issue
- Performance Issue
- Refactor / Technical Debt
- Documentation
- Generic

---

## Extended Documentation

Additional documentation can be found in the [`docs/`](./docs/) folder:

- [Architecture](./docs/architecture/mvvm-overview.md) → MVVM, navigation diagram, UML.
- [Guides](./docs/guides/setup.md) → Setup instructions, Git workflow, testing.
- [Design](./docs/design/style-guide.md) → UI/UX guidelines, accessibility, screenshots.
- [API](./docs/api/backend-integration.md) → Backend endpoints and data flow.
- [References](./docs/references/bibliography.md) → Official docs, articles, resources.

## Security

If you discover a security vulnerability, please follow the instructions in [SECURITY.md](./SECURITY.md).  
**Do not disclose details publicly in issues or pull requests.**

---

## Authors

See the complete list of contributors in [AUTHORS.md](./AUTHORS.md).

---


## Project status
This is an **academic and private** project, developed by the Software Engineering team – UPC.  
External contributions are **not accepted**.







