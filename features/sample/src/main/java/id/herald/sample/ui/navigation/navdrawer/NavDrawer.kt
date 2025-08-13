package id.herald.sample.ui.navigation.navdrawer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import id.herald.core.util.Dimens
import id.herald.sample.ui.navigation.MainNavHost
import id.herald.sample.ui.navigation.model.BottomBar
import id.herald.sample.ui.navigation.navdrawer.sections.NavDrawerSection
import kotlinx.coroutines.launch

/** Created by Herald Santos on 04/12/2024. */

@Composable
fun NavDrawer(
    modifier: Modifier = Modifier,
    navigationItemContentList: List<BottomBar>,
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape
            ) {
                NavDrawerSection(
                    modifier = modifier,
                    navigationItemContentList = navigationItemContentList,
                    navController = navController,
                    currentDestination = currentDestination,
                    onDrawerAction = {
                        coroutineScope.launch {
                            if (drawerState.isOpen) drawerState.close() else drawerState.open()
                        }
                    },
                )
            }
        }
    ) {
        MainNavHost(navController = navController, innerPadding = PaddingValues(end = Dimens.dp0))
    }
}