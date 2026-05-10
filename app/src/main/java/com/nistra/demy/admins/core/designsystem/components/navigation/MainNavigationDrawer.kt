package com.nistra.demy.admins.core.designsystem.components.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.model.DrawerSection
import com.nistra.demy.admins.core.navigation.model.DrawerDestination

/**
 * Main navigation drawer with header and content.
 *
 * @param appName Name of the application.
 * @param sections List of drawer sections to display.
 * @param selectedDestinationId Currently selected destination ID.
 * @param onDestinationClick Callback when a destination is clicked.
 * @author Salim Ramirez
 */
@Composable
fun MainNavigationDrawer(
    appName: String,
    sections: List<DrawerSection>,
    selectedDestinationId: String,
    onDestinationClick: (DrawerDestination) -> Unit
) {
    PermanentDrawerSheet(modifier = Modifier.width(304.dp)) {
        DrawerHeader(appName = appName)
        Spacer(Modifier.height(16.dp))
        DrawerContent(
            sections = sections,
            selectedId = selectedDestinationId,
            onDestinationClick = onDestinationClick
        )
    }
}
