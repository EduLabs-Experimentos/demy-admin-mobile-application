package com.nistra.demy.admins.core.designsystem.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.navigation.MainNavigationDrawer
import com.nistra.demy.admins.core.designsystem.components.navigation.MainTopBar
import com.nistra.demy.admins.core.designsystem.model.DrawerSection
import com.nistra.demy.admins.core.designsystem.model.UserUi
import com.nistra.demy.admins.core.designsystem.preview.TabletPreviewDark
import com.nistra.demy.admins.core.designsystem.preview.TabletPreviewLight
import com.nistra.demy.admins.core.designsystem.theme.DemyTheme
import com.nistra.demy.admins.core.navigation.model.DrawerDestination

/**
 * Main layout composable with a permanent navigation drawer and a top app bar.
 *
 * This is a pure UI component that composes the main screen structure.
 * It delegates navigation logic to the caller.
 *
 * Designed for tablet devices in landscape orientation.
 *
 * @param title Title shown in the top app bar.
 * @param user User information for the user chip.
 * @param showBackButton Whether to show a back button in the app bar.
 * @param onBackClick Callback when the back button is clicked.
 * @param onUserClick Callback when the user chip is clicked.
 * @param isProfileSelected Whether the profile is currently selected.
 * @param appName Name of the application shown in the drawer header.
 * @param drawerSections Sections and items for the navigation drawer.
 * @param selectedDestinationId Currently selected destination ID.
 * @param onDestinationClick Callback when a navigation item is clicked.
 * @param content Main content composable.
 * @author Salim Ramirez
 */
@Composable
fun MainLayout(
    title: String,
    user: UserUi,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    onUserClick: (() -> Unit)? = null,
    isProfileSelected: Boolean = false,
    appName: String,
    drawerSections: List<DrawerSection>,
    selectedDestinationId: String,
    onDestinationClick: (DrawerDestination) -> Unit,
    content: @Composable () -> Unit
) {
    PermanentNavigationDrawer(
        drawerContent = {
            MainNavigationDrawer(
                appName = appName,
                sections = drawerSections,
                selectedDestinationId = selectedDestinationId,
                onDestinationClick = onDestinationClick
            )
        }
    ) {
        Scaffold(
            topBar = {
                MainTopBar(
                    title = title,
                    user = user,
                    showBackButton = showBackButton,
                    onBackClick = onBackClick,
                    onUserClick = onUserClick,
                    isProfileSelected = isProfileSelected
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxSize()
            ) {
                content()
            }
        }
    }
}


/**
 * Light and dark theme preview of the MainLayout composable.
 *
 * @author Salim Ramirez
 */
@TabletPreviewLight
@TabletPreviewDark
@Composable
fun MainLayoutPreview() {
    DemyTheme {
        MainLayout(
            title = "Dashboard",
            user = UserUi(
                name = "Salim",
                role = "Administrator",
                avatarResId = null
            ),
            showBackButton = true,
            onBackClick = { },
            appName = "Demy Admins",
            drawerSections = listOf(
                DrawerSection(
                    headerResId = R.string.drawer_section_overview,
                    items = listOf(DrawerDestination.Dashboard)
                ),
                DrawerSection(
                    headerResId = R.string.drawer_section_academy,
                    items = listOf(DrawerDestination.Teachers, DrawerDestination.Students)
                )
            ),
            selectedDestinationId = DrawerDestination.Dashboard.id,
            onDestinationClick = { }
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material3.Text("Dashboard content")
            }
        }
    }
}
