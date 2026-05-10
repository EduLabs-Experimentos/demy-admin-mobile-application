package com.nistra.demy.admins

import android.content.pm.ActivityInfo
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlin.math.min

/**
 * Restricts app usage to tablets in landscape mode.
 *
 * Checks the device's smallest width in dp using [LocalWindowInfo].
 * If it's under 600 dp, shows a dialog with a message and an "Accept" button that closes the app.
 * The dialog is shown in portrait orientation for better UX on phones.
 * Otherwise, renders the given [content] in landscape orientation.
 *
 * @param content Main composable content shown on supported devices.
 * @author Salim Ramirez
 */
@Composable
fun AppGate(content: @Composable () -> Unit) {
    val activity = LocalActivity.current
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current

    val widthPx = windowInfo.containerSize.width
    val heightPx = windowInfo.containerSize.height
    val smallestWidthDp = with(density) { min(widthPx, heightPx).toDp().value }

    if (smallestWidthDp < 600f) {
        // Change orientation to portrait for non-tablet devices
        DisposableEffect(Unit) {
            val originalOrientation = activity?.requestedOrientation
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            onDispose {
                originalOrientation?.let { activity.requestedOrientation = it }
            }
        }

        AlertDialog(
            onDismissRequest = { /* No dismissible */ },
            title = { Text(text = stringResource(id = R.string.tablet_only_title)) },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.demy_combination_mark_original),
                        contentDescription = "Demy Logo",
                        modifier = Modifier
                            .size(150.dp)
                            .padding(vertical = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = stringResource(id = R.string.tablet_only_message))
                }
            },
            confirmButton = {
                TextButton(onClick = { activity?.finish() }) {
                    Text(text = stringResource(id = R.string.accept))
                }
            }
        )
    } else {
        content()
    }
}
