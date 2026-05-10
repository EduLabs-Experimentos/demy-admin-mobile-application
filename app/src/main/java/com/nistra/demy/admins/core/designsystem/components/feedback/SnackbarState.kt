package com.nistra.demy.admins.core.designsystem.components.feedback

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.nistra.demy.admins.core.common.LocaleManager
import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.core.common.SnackbarType
import com.nistra.demy.admins.core.localization.AndroidLocaleManager

/**
 * State holder for the Demy Snackbar system.
 *
 * This class manages both the [SnackbarHostState] and the current [SnackbarType],
 * ensuring that the type is updated synchronously with the message display.
 *
 * @property hostState The underlying [SnackbarHostState].
 * @property currentType The current [SnackbarType] being displayed.
 *
 * @author Salim Ramirez
 */
class DemySnackbarState(
    val hostState: SnackbarHostState,
    internal val currentType: MutableState<SnackbarType>
)

/**
 * A composable function that remembers and manages a [DemySnackbarState].
 *
 * This is a convenience function that creates and remembers a DemySnackbarState
 * instance for use with the Demy Snackbar system.
 *
 * @return A remembered [DemySnackbarState] instance.
 *
 * @author Salim Ramirez
 */
@Composable
fun rememberDemySnackbarState(): DemySnackbarState {
    val hostState = remember { SnackbarHostState() }
    val currentType = remember { mutableStateOf(SnackbarType.INFO) }
    return remember { DemySnackbarState(hostState, currentType) }
}

/**
 * Displays a Snackbar message when a message is provided.
 *
 * This effect launches whenever the [message] changes and shows the Snackbar
 * with the provided message content. The type is updated BEFORE showing the
 * Snackbar to prevent any color flash.
 *
 * The message is resolved using [LocaleManager] to support localization.
 * The Snackbar will automatically dismiss after a short duration (4 seconds),
 * but can also be dismissed manually by clicking the action button.
 *
 * @param message The message to display, or null if no message should be shown.
 * @param snackbarState The [DemySnackbarState] used to manage the Snackbar.
 * @param onMessageShown Callback invoked after the message is shown to clear the state.
 *
 * @author Salim Ramirez
 */
@Composable
fun SnackbarEffect(
    message: SnackbarMessage?,
    snackbarState: DemySnackbarState,
    onMessageShown: () -> Unit
) {
    val context = LocalContext.current
    val localeManager: LocaleManager = remember { AndroidLocaleManager(context) }

    LaunchedEffect(message) {
        message?.let {
            // Update type BEFORE showing Snackbar to prevent flash
            snackbarState.currentType.value = it.type

            // Resolve localized strings
            val resolvedMessage = localeManager.getString(it.message)
            val resolvedActionLabel = it.actionLabel?.let { label ->
                localeManager.getString(label)
            }

            snackbarState.hostState.showSnackbar(
                message = resolvedMessage,
                actionLabel = resolvedActionLabel,
                duration = SnackbarDuration.Short
            )
            onMessageShown()
        }
    }
}

