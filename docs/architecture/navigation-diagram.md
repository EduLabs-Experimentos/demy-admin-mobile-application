# Navigation Diagram – Demy Android App (Admin)

This document describes the screen flow and navigation graph of the **Demy Android** app for the **administrator** segment.

---

## 1) High-level Flow

```
[Unauthenticated]
        |
        v
LoginScreen
        |
        v
[Authenticated]
        |
        v
Home (Tabs)
    ├─ DashboardTab
    ├─ UsersTab
    └─ ReportsTab
        |
        ├─ UserDetailScreen (from UsersTab)
        └─ ReportDetailScreen (from ReportsTab)
        |
        ├─ ProfileScreen
        └─ SettingsScreen
```

Session rules:
- On successful login → replace back stack with **Home**.
- On session expiry → navigate to **Login** and clear back stack.

---

## 2) Routes & Arguments

**Top-level**
- `login`
- `home` (Tabbed: `dashboard`, `users`, `reports`)

**Details & Utilities**
- `user_detail/{userId}`
    - args: `userId: String`
- `report_detail/{reportId}?filter={filter}`
    - args: `reportId: String` (required), `filter: String?` (optional)
- `profile`
- `settings`

---

## 3) Deep Links (examples)

- App base: `demy://app`

| Screen         | Deep link                              |
|----------------|----------------------------------------|
| Login          | `demy://app/login`                     |
| Home (default) | `demy://app/home`                      |
| Users tab      | `demy://app/home/users`                |
| User detail    | `demy://app/user/u123`                 |
| Reports tab    | `demy://app/home/reports`              |
| Report detail  | `demy://app/report/r42?filter=monthly` |

Notes:
- Deep link to `home/users` opens the app in Home with Users tab selected.
- Deep link to `user_detail` requires prior auth; if no session, redirect to `login` → post-login navigate to original target.

---

## 4) Back Stack Policy

- **Login → Home**: `popUpTo("login") { inclusive = true }`
- **Tabs** (`dashboard`, `users`, `reports`): `singleTop = true`, preserve state per tab.
- **Detail screens** (`user_detail`, `report_detail`): normal push; back returns to the originating tab.
- **Profile/Settings** from Home: push on stack; back returns to last tab.
- **From deep link** to detail without session:
    1) Navigate to `login` (clear stack)
    2) After success, navigate to intended route (`user_detail/...`)

---

## 5) App Bars & Up Navigation

- **Home**: shows TopAppBar with title of current tab and actions (search, filter where applicable).
- **Detail screens**: show Up button; `navigateUp()` returns to previous tab or list.
- **Login**: no Up button (entry point).

---

## 6) Sample Navigation Graph (Compose pseudo-code)

```
NavHost(
    navController = navController,
    startDestination = if (isAuthenticated) "home" else "login"
) {
    composable("login") {
        LoginScreen(
            onLoginSuccess = {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                    launchSingleTop = true
                }
            }
        )
    }

    navigation(startDestination = "home/dashboard", route = "home") {
        composable("home/dashboard") { DashboardScreen() }
        composable("home/users") {
            UsersScreen(
                onUserSelected = { userId ->
                    navController.navigate("user_detail/$userId")
                }
            )
        }
        composable(
            route = "home/reports"
        ) { ReportsScreen() }
    }

    composable(
        route = "user_detail/{userId}",
        arguments = listOf(navArgument("userId") { type = NavType.StringType })
    ) { backStackEntry ->
        val userId = backStackEntry.arguments?.getString("userId")!!
        UserDetailScreen(userId = userId)
    }

    composable(
        route = "report_detail/{reportId}?filter={filter}",
        arguments = listOf(
            navArgument("reportId") { type = NavType.StringType },
            navArgument("filter") { type = NavType.StringType; nullable = true; defaultValue = null }
        )
    ) { backStackEntry ->
        val reportId = backStackEntry.arguments?.getString("reportId")!!
        val filter = backStackEntry.arguments?.getString("filter")
        ReportDetailScreen(reportId = reportId, filter = filter)
    }

    composable("profile") { ProfileScreen() }
    composable("settings") { SettingsScreen() }
}
```

---

## 7) Tab Behavior (Home)

- **Default tab**: `dashboard`
- **State restoration**: each tab maintains its scroll position and filters.
- **Re-selecting current tab**: scroll-to-top or state reset (optional, team decision).

---

## 8) Guards & Preconditions

- **AuthGuard**: any route other than `login` requires active session.
    - If missing/expired, redirect to `login` and queue the intended destination.
- **Role checks** (admins only): gated features (e.g., user management, reports export) validate `role == "admin"`.

---

## 9) Navigation Errors & Recovery

- If a route argument is missing/invalid → show fallback (empty state or toast) and `navigateUp()`.
- Network-dependent details (user/report) should show:
    - Loading → Content
    - Error → Retry / Navigate back

---

## 10) Future Extensions

- Nested graph for **Settings** (e.g., Notifications, Language, Theme).
- Modal bottom sheet routes for quick actions (filters, sort).
- Multi-pane layouts for tablets (list + detail side-by-side).

---
