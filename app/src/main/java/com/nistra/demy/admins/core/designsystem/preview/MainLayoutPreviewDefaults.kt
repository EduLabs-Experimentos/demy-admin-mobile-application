package com.nistra.demy.admins.core.designsystem.preview

import androidx.compose.runtime.Composable
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.theme.DemyTheme
import com.nistra.demy.admins.core.navigation.model.DrawerDestination
import com.nistra.demy.admins.core.designsystem.layout.MainLayout
import com.nistra.demy.admins.core.designsystem.model.DrawerSection
import com.nistra.demy.admins.core.designsystem.model.UserUi

/**
 * Defaults for previewing the MainLayout composable.
 *
 * Includes a sample user and drawer sections.
 *
 * @author Salim Ramirez
 */
object MainLayoutPreviewDefaults {
    val user = UserUi(name = "Salim", role = "Administrator", avatarResId = null)
    val sections = listOf(
        DrawerSection(R.string.drawer_section_overview, listOf(DrawerDestination.Dashboard)),
        DrawerSection(R.string.drawer_section_academy, listOf(DrawerDestination.Teachers, DrawerDestination.Students))
    )
}

/**
 * Container for previewing the MainLayout composable.
 *
 * Sets up the DemyTheme and provides default parameters.
 *
 * @param title The title to display in the top app bar.
 * @param appName The name of the application.
 * @param selectedDestinationId The currently selected destination ID.
 * @param content The main content composable.
 * @author Salim Ramirez
 */
@Composable
fun MainLayoutPreviewContainer(
    title: String = "Preview",
    appName: String = "Demy Admins",
    selectedDestinationId: String = DrawerDestination.Dashboard.id,
    content: @Composable () -> Unit
) {
    DemyTheme {
        MainLayout(
            title = title,
            user = MainLayoutPreviewDefaults.user,
            showBackButton = false,
            onBackClick = null,
            appName = appName,
            drawerSections = MainLayoutPreviewDefaults.sections,
            selectedDestinationId = selectedDestinationId,
            onDestinationClick = { },
            content = content
        )
    }
}
