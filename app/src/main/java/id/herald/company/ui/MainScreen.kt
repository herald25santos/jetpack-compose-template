package id.herald.company.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.herald.core.ui.theme.ShopeeTheme
import id.herald.company.utils.NavigationType
import id.herald.sample.ui.navigation.BottomNav
import id.herald.sample.ui.navigation.NavRail
import id.herald.sample.ui.navigation.model.BottomBarScreen
import id.herald.sample.ui.navigation.navdrawer.NavDrawer

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigationType: NavigationType,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navigationItemContentList = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Cart,
        BottomBarScreen.Profile
    )

    when (navigationType) {
        NavigationType.BOTTOM_NAVIGATION -> {
            BottomNav(
                modifier = modifier,
                navigationItemContentList = navigationItemContentList,
                navController = navController,
                currentDestination = currentDestination,
            )
        }

        NavigationType.NAVIGATION_RAIL -> {
            NavRail(
                modifier = modifier,
                navigationItemContentList = navigationItemContentList,
                navController = navController,
                currentDestination = currentDestination,
            )
        }

        NavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            NavDrawer(
                modifier = modifier,
                navigationItemContentList = navigationItemContentList,
                navController = navController,
                currentDestination = currentDestination,
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DefaultPreview() {
    ShopeeTheme {
        MainScreen(navigationType = NavigationType.BOTTOM_NAVIGATION)
    }
}