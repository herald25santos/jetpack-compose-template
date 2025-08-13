package id.herald.company.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import id.herald.company.utils.NavigationType

/** Created by Herald Santos on 04/12/2024. */

@Composable
fun MainApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val navigationType: NavigationType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            NavigationType.BOTTOM_NAVIGATION
        }

        WindowWidthSizeClass.Medium -> {
            NavigationType.NAVIGATION_RAIL
        }

        WindowWidthSizeClass.Expanded -> {
            NavigationType.PERMANENT_NAVIGATION_DRAWER
        }

        else -> {
            NavigationType.BOTTOM_NAVIGATION
        }
    }

    MainScreen(navigationType = navigationType, modifier = modifier)
}