package com.nistra.demy.admins.core.designsystem.samples

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.navigation.model.DrawerDestination
import com.nistra.demy.admins.core.designsystem.layout.MainLayout
import com.nistra.demy.admins.core.designsystem.model.DrawerSection
import com.nistra.demy.admins.core.designsystem.model.UserUi
import com.nistra.demy.admins.core.designsystem.preview.TabletPreviewDark
import com.nistra.demy.admins.core.designsystem.preview.TabletPreviewLight

/**
 * Demonstrates how to use [TabletPreviewLight].
 *
 * @author Salim Ramirez
 */
@TabletPreviewLight
@Composable
fun TabletLightPreviewSample() {
    MainLayout(
        title = "Dashboard",
        user = UserUi(
            name = "Paul",
            role = "Administrator",
            avatarResId = null
        ),
        showBackButton = true,
        onBackClick = null,
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
        Text("Preview in Light Mode 🌞")
    }
}

/**
 * Demonstrates how to use [TabletPreviewDark].
 *
 * @author Salim Ramirez
 */
@TabletPreviewDark
@Composable
fun TabletDarkPreviewSample() {
    MainLayout(
        title = "Dashboard",
        user = UserUi(
            name = "Daniel",
            role = "Administrator",
            avatarResId = null
        ),
        showBackButton = true,
        onBackClick = null,
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
        Text("Preview in Dark Mode 🌙")
    }
}
