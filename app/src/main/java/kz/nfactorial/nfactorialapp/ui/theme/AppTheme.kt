package kz.nfactorial.nfactorialapp.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    val rippleIndication = rememberRipple()
    val context = LocalContext.current
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val typography = if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT &&
        windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT) {
        appTypographySmall
    } else {
        appTypography
    }
    CompositionLocalProvider(
        LocalColors provides appColors,
        LocalIndication provides rippleIndication,
        LocalTypography provides typography,
    ) {
        content()
    }
}

object AppTheme {

    val colors: Colors
        @Composable
        get() = appColors

    val fonts: Typography
        @Composable
        get() {
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
            return if (windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT) {
                appTypographySmall
            } else {
                appTypography
            }
        }
}

sealed class WindowSizeClass(private val value: Int): Comparable<WindowSizeClass> {
    object Compact: WindowSizeClass(1)
    object Medium: WindowSizeClass(2)
    object Expanded: WindowSizeClass(3)

    override fun compareTo(other: WindowSizeClass): Int {
        return value.compareTo(other.value)
    }
}
