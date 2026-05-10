package com.nistra.demy.admins.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

@Immutable
data class ExtendedColorScheme(
    val info: ColorFamily,
    val success: ColorFamily,
    val warning: ColorFamily
)

internal val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColorScheme(
        info = ColorFamily(Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified),
        success = ColorFamily(Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified),
        warning = ColorFamily(Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified),
    )
}

val MaterialTheme.extendedColors: ExtendedColorScheme
    @Composable
    get() = LocalExtendedColors.current

// Light mode
object ExtendedPalettes {
    val Light = ExtendedColorScheme(
        info = ColorFamily(infoLight, onInfoLight, infoContainerLight, onInfoContainerLight),
        success = ColorFamily(successLight, onSuccessLight, successContainerLight, onSuccessContainerLight),
        warning = ColorFamily(warningLight, onWarningLight, warningContainerLight, onWarningContainerLight)
    )

    val Dark = ExtendedColorScheme(
        info = ColorFamily(infoDark, onInfoDark, infoContainerDark, onInfoContainerDark),
        success = ColorFamily(successDark, onSuccessDark, successContainerDark, onSuccessContainerDark),
        warning = ColorFamily(warningDark, onWarningDark, warningContainerDark, onWarningContainerDark)
    )
}
