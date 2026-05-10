# MVVM Overview – Demy Android App

The **Demy Android App** follows the **MVVM (Model–View–ViewModel)** architecture pattern.  
This approach separates responsibilities, improves maintainability, and allows the team to scale the codebase as the project grows.

---

## 1. Layers

### View (UI)
- Built with **Jetpack Compose**.
- Displays state exposed by the `ViewModel`.
- Uses **state hoisting** to keep UI stateless when possible.
- Example: `LoginScreen`, `HomeScreen`, `ProfileScreen`.

### ViewModel
- Holds UI state and exposes it as **StateFlow** or **LiveData**.
- Handles user interactions and transforms them into state updates.
- Calls use cases (from the domain layer) or repositories (from the data layer).
- Example: `LoginViewModel`, `HomeViewModel`.

### Model (Domain + Data)
- **Domain**: Business logic and use cases.  
  Example: `AuthenticateUserUseCase`.
- **Data**: Provides data from local (Room) or remote (API) sources.  
  Example: `UserRepository`, `AuthRepository`.

---

## 2. Package Structure

The project is a **single module** app, but organized by **features** and **core**:

```
app/
├─ core/                  # Shared components: navigation, theme, utilities
└─ features/
    ├─ login/             # Login screen and logic
    │   ├─ ui/            # Compose screens
    │   ├─ viewmodel/     # LoginViewModel
    │   └─ data/          # AuthRepository, local/remote data sources
    ├─ home/              # Home screen and dashboard
    ├─ profile/           # Profile and settings
    └─ ...
```
---

## 3. Data Flow Example

1. User enters credentials in the **LoginScreen**.
2. `LoginViewModel` calls `AuthenticateUserUseCase`.
3. The use case queries the **AuthRepository**.
4. Repository fetches data from:
    - **Remote API** (via Retrofit + Coroutines).
    - Or **Room DB** if working offline.
5. Result (success/error) is returned to the `ViewModel`.
6. `ViewModel` updates the UI state.
7. Compose recomposes the `LoginScreen` with the new state.

---

## 4. Benefits

- **Separation of concerns**: UI code is not mixed with business logic.
- **Testability**: ViewModels and use cases can be tested independently.
- **Scalability**: Each feature is encapsulated and grows without breaking others.
- **Maintainability**: Clear structure (`core/` vs `features/`) makes it easy for new team members to understand.

---
