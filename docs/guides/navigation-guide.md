# Navigation Guide - Demy Admins

## Navigation Structure

The application uses a three-level navigation architecture:

```
Root Level (RootNavGraph)
├── Splash
├── AuthGraph (AuthNavHost)
│   ├── SignIn
│   ├── SignUp
│   ├── VerifyEmail
│   ├── CompleteAccount
│   └── SetUpAcademy
└── MainGraph (MainNavHost)
    ├── main/dashboard (Parent graph)
    │   └── dashboard/overview
    ├── main/teachers (Parent graph)
    │   └── teachers/register
    └── main/students (Parent graph)
        └── students/register
```

## How to Add a New Section to the Drawer

### 1. Create Destination Definitions

**File:** `features/{module}/navigation/{Module}Destination.kt`

```kotlin
sealed interface CoursesDestination {
    val route: String

    data object List : CoursesDestination {
        override val route = "courses/list"
        fun toRoute() = route
    }
}
```

### 2. Add the Main Route in MainDestination

**File:** `features/main/presentation/navigation/MainDestination.kt`

```kotlin
data object Courses : MainDestination {
    override val route = "main/courses"
    fun toRoute() = route
}
```

### 3. Create the Navigation Graph

**File:** `features/{module}/navigation/{Module}NavGraph.kt`

```kotlin
fun NavGraphBuilder.coursesGraph(navController: NavHostController) {
    navigation(
        startDestination = CoursesDestination.List.route,
        route = MainDestination.Courses.route  // ← Parent graph route
    ) {
        composable(CoursesDestination.List.toRoute()) { 
            CoursesScreen() 
        }
    }
}
```

### 4. Update DrawerDestination

**File:** `core/navigation/model/DrawerDestination.kt`

```kotlin
data object Courses : DrawerDestination(
    MainDestination.Courses.route,  // ← Must match parent graph
    "Courses", 
    Icons.AutoMirrored.Filled.MenuBook
)
```

### 5. Add the Graph to MainNavHost

**File:** `features/main/presentation/navigation/MainNavHost.kt`

```kotlin
NavHost(
    navController = innerNavController,
    startDestination = MainDestination.Dashboard.route
) {
    dashboardGraph(innerNavController)
    teachersGraph(innerNavController)
    studentsGraph(innerNavController)
    coursesGraph(innerNavController)  // ← Add here
}
```

### 6. Update MainNavExtensions

**File:** `features/main/presentation/navigation/MainNavExtensions.kt`

**A. Add to centralized module map:**
```kotlin
private val moduleToParentRouteMap = mapOf(
    "dashboard" to MainDestination.Dashboard.route,
    "teachers" to MainDestination.Teachers.route,
    "students" to MainDestination.Students.route,
    "courses" to MainDestination.Courses.route  // ← Only add here
)
```

**B. Add title in currentTitle():**
```kotlin
fun NavHostController.currentTitle(): String {
    val parentRoute = currentParentRoute()
    return when (parentRoute) {
        MainDestination.Dashboard.route -> "Dashboard"
        MainDestination.Teachers.route -> "Teachers"
        MainDestination.Students.route -> "Students"
        MainDestination.Courses.route -> "Courses"  // ← Add here
        else -> "Demy Admins"
    }
}
```

**IMPORTANT NOTE:**
With the new centralized mapping system, you only need to add one entry in `moduleToParentRouteMap`.
This will automatically work for ALL module destinations:
- `courses/list` → `main/courses`
- `courses/create` → `main/courses`
- `courses/edit/{id}` → `main/courses`
- `courses/details/{id}` → `main/courses`

### 7. Enable Navigation in MainShell

**File:** `core/designsystem/layout/MainShell.kt`

```kotlin
val implementedRoutes = setOf(
    DrawerDestination.Dashboard.id,
    DrawerDestination.Teachers.id,
    DrawerDestination.Students.id,
    DrawerDestination.Courses.id  // ← Add here
)
```

## Important Rules

### DO

1. **Use consistent route format:**
   - Parent graph: `"main/{module}"`
   - Child destination: `"{module}/{screen}"`

2. **Verify matching:**
   - `MainDestination.{Module}.route` = `"main/{module}"`
   - `DrawerDestination.{Module}.id` = `MainDestination.{Module}.route`
   - First segment of child route = module name

3. **Add documentation when making changes**

4. **Test navigation:**
   - Navigate to the new section
   - Verify it's highlighted in the Drawer
   - Press twice to confirm no flickering

### DON'T

1. **Don't use hardcoded routes** in multiple places
2. **Don't forget to update** `implementedRoutes` in MainShell
3. **Don't change the naming pattern** without updating `currentParentRoute()`
4. **Don't navigate directly** to child routes, use parent graph route

## Debugging

If Drawer selection doesn't work:

1. Verify that `DrawerDestination.{Module}.id == MainDestination.{Module}.route`
2. Verify that the nested graph uses `route = MainDestination.{Module}.route`
3. Verify that you added the mapping in `currentParentRoute()`
4. Verify that you added the title in `currentTitle()`

If there's flickering when navigating:

1. Verify that `navigateOnce()` correctly compares routes
2. Verify that the parent graph is configured correctly

## Handling Multiple Destinations in a Module

### Example: Teachers Module with Multiple Screens

```kotlin
// TeachersDestination.kt
sealed interface TeachersDestination {
    val route: String

    data object Register : TeachersDestination {
        override val route = "teachers/register"
        fun toRoute() = route
    }

    data object List : TeachersDestination {
        override val route = "teachers/list"
        fun toRoute() = route
    }

    data object Edit : TeachersDestination {
        override val route = "teachers/edit/{id}"
        fun toRoute(id: String) = "teachers/edit/$id"
    }

    data object Details : TeachersDestination {
        override val route = "teachers/details/{id}"
        fun toRoute(id: String) = "teachers/details/$id"
    }
}

// TeachersNavGraph.kt
fun NavGraphBuilder.teachersGraph(navController: NavHostController) {
    navigation(
        startDestination = TeachersDestination.List.route,  // ← Changed to List
        route = MainDestination.Teachers.route
    ) {
        composable(TeachersDestination.Register.toRoute()) { 
            TeachersRegisterScreen() 
        }
        composable(TeachersDestination.List.toRoute()) { 
            TeachersListScreen(
                onEditTeacher = { id -> 
                    navController.navigate(TeachersDestination.Edit.toRoute(id)) 
                },
                onViewDetails = { id ->
                    navController.navigate(TeachersDestination.Details.toRoute(id))
                }
            ) 
        }
        composable(
            route = TeachersDestination.Edit.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val teacherId = backStackEntry.arguments?.getString("id") ?: ""
            TeachersEditScreen(teacherId = teacherId)
        }
        composable(
            route = TeachersDestination.Details.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val teacherId = backStackEntry.arguments?.getString("id") ?: ""
            TeachersDetailsScreen(teacherId = teacherId)
        }
    }
}
```

### The Magic of the System

With a single entry in `moduleToParentRouteMap`:

```kotlin
"teachers" to MainDestination.Teachers.route
```

**All these routes automatically map to the same parent graph:**
- `teachers/register` → `main/teachers`
- `teachers/list` → `main/teachers`
- `teachers/edit/123` → `main/teachers`
- `teachers/details/456` → `main/teachers`

**Result:** The Drawer keeps "Teachers" selected regardless of which screen in the module you're on.

## Complete Example

See the `Teachers` implementation as reference:

- `TeachersDestination.kt` - Defines child routes
- `MainDestination.Teachers` - Defines parent graph route
- `TeachersNavGraph.kt` - Configures nested graph
- `DrawerDestination.Teachers` - Uses parent graph route
- `MainNavHost.kt` - Registers the graph
- `MainNavExtensions.kt` - Maps routes (single entry)
- `MainShell.kt` - Enables navigation

