package com.nistra.demy.admins.core.designsystem.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.components.text.UserChip
import com.nistra.demy.admins.core.designsystem.model.UserUi

/**
 * Top app bar for the main layout.
 *
 * @param title Title to display in the app bar.
 * @param user User information for the user chip.
 * @param showBackButton Whether to show a back button.
 * @param onBackClick Callback when the back button is clicked.
 * @param onUserClick Callback when the user chip is clicked.
 * @param isProfileSelected Whether the profile is currently selected.
 * @author Salim Ramirez
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    title: String,
    user: UserUi,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    onUserClick: (() -> Unit)? = null,
    isProfileSelected: Boolean = false
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (showBackButton && onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Navigate back"
                    )
                }
            }
        },
        actions = {
            UserChip(user = user, onClick = onUserClick, isSelected = isProfileSelected)
        },
        modifier = Modifier.shadow(elevation = 4.dp)
    )
}

