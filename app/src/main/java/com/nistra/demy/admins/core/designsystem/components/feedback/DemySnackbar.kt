package com.nistra.demy.admins.core.designsystem.components.feedback

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.common.SnackbarType
import com.nistra.demy.admins.core.designsystem.theme.extendedColors

/**
 * A Material Design 3 Snackbar component with semantic color support and icons.
 *
 * This composable provides a pre-configured Snackbar that automatically applies
 * appropriate colors and icons based on the message type (SUCCESS, ERROR, WARNING, INFO).
 * It uses Material Design 3 extended colors for consistent visual feedback.
 *
 * The Snackbar ensures proper color contrast for accessibility by using
 * container and onContainer color pairs. Each type has its corresponding icon:
 * - SUCCESS: CheckCircle icon (green)
 * - ERROR: Error icon (red)
 * - WARNING: Warning icon (amber/yellow)
 * - INFO: Info icon (blue)
 *
 * @param snackbarData The data for the Snackbar, including message and action.
 * @param type The semantic type of the message, determining the color scheme and icon.
 * @param modifier Optional [Modifier] for the Snackbar.
 *
 * @author Salim Ramirez
 */
@Composable
fun DemySnackbar(
    snackbarData: SnackbarData,
    type: SnackbarType,
    modifier: Modifier = Modifier
) {
    val (containerColor, contentColor) = getSnackbarColors(type)
    val icon = getSnackbarIcon(type)

    Snackbar(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        actionContentColor = contentColor,
        action = snackbarData.visuals.actionLabel?.let {
            {
                TextButton(onClick = { snackbarData.performAction() }) {
                    Text(
                        text = it,
                        color = contentColor
                    )
                }
            }
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = snackbarData.visuals.message,
                color = contentColor
            )
        }
    }
}

/**
 * A Snackbar host that displays semantic Snackbar messages.
 *
 * This composable wraps [SnackbarHost] and automatically applies the correct
 * color scheme based on the provided [SnackbarType]. It's designed to be used
 * with the Demy Design System and respects Material Design 3 guidelines.
 *
 * Usage:
 * ```
 * val snackbarState = rememberDemySnackbarState()
 *
 * SnackbarEffect(
 *     message = uiState.snackbarMessage,
 *     snackbarState = snackbarState,
 *     onMessageShown = viewModel::clearSnackbarMessage
 * )
 *
 * Box {
 *     // Your content
 *     DemySnackbarHost(
 *         snackbarState = snackbarState,
 *         modifier = Modifier.align(Alignment.BottomCenter)
 *     )
 * }
 * ```
 *
 * @param snackbarState The [DemySnackbarState] that manages Snackbar display and type.
 * @param modifier Optional [Modifier] for the SnackbarHost.
 *
 * @author Salim Ramirez
 */
@Composable
fun DemySnackbarHost(
    snackbarState: DemySnackbarState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(
        hostState = snackbarState.hostState,
        modifier = modifier,
        snackbar = { data ->
            DemySnackbar(
                snackbarData = data,
                type = snackbarState.currentType.value
            )
        }
    )
}

/**
 * Returns the appropriate container and content colors for a Snackbar based on its type.
 *
 * This function maps semantic types to Material Design 3 color schemes:
 * - SUCCESS: Uses extended success colors (green)
 * - ERROR: Uses Material error colors (red)
 * - WARNING: Uses extended warning colors (amber/yellow)
 * - INFO: Uses extended info colors (blue)
 *
 * The function ensures proper contrast by returning container and onContainer
 * color pairs for accessibility compliance.
 *
 * @param type The semantic type of the Snackbar message.
 * @return A [Pair] of container color and content color.
 *
 * @author Salim Ramirez
 */
@Composable
private fun getSnackbarColors(type: SnackbarType): Pair<Color, Color> {
    val extendedColors = MaterialTheme.extendedColors
    val standardColors = MaterialTheme.colorScheme

    return when (type) {
        SnackbarType.SUCCESS -> Pair(
            extendedColors.success.colorContainer,
            extendedColors.success.onColorContainer
        )
        SnackbarType.ERROR -> Pair(
            standardColors.errorContainer,
            standardColors.onErrorContainer
        )
        SnackbarType.WARNING -> Pair(
            extendedColors.warning.colorContainer,
            extendedColors.warning.onColorContainer
        )
        SnackbarType.INFO -> Pair(
            extendedColors.info.colorContainer,
            extendedColors.info.onColorContainer
        )
    }
}

/**
 * Returns the appropriate icon for a Snackbar based on its type.
 *
 * This function maps semantic types to Material Icons:
 * - SUCCESS: CheckCircle icon
 * - ERROR: Error icon
 * - WARNING: Warning icon
 * - INFO: Info icon
 *
 * These icons provide visual reinforcement of the message type,
 * improving accessibility and user comprehension.
 *
 * @param type The semantic type of the Snackbar message.
 * @return An [ImageVector] representing the appropriate icon.
 *
 * @author Salim Ramirez
 */
private fun getSnackbarIcon(type: SnackbarType): ImageVector {
    return when (type) {
        SnackbarType.SUCCESS -> Icons.Filled.CheckCircle
        SnackbarType.ERROR -> Icons.Filled.Error
        SnackbarType.WARNING -> Icons.Filled.Warning
        SnackbarType.INFO -> Icons.Filled.Info
    }
}

