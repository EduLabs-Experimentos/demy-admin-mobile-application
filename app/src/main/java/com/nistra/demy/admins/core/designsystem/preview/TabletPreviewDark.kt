package com.nistra.demy.admins.core.designsystem.preview

import androidx.compose.ui.tooling.preview.Preview
import android.content.res.Configuration

/**
 * Preview for dark mode on tablet landscape (1280x800dp @240dpi).
 *
 * Use this to preview your composables as they would appear
 * on a tablet device in landscape orientation using the light theme.
 *
 * @sample com.nistra.demy.admins.core.designsystem.samples.TabletDarkPreviewSample
 * @author Salim Ramirez
 */
@Preview(
    name = "Tablet - Dark",
    device = "spec:width=1280dp,height=800dp,dpi=240",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class TabletPreviewDark
