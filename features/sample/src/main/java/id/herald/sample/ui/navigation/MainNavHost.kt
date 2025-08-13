package id.herald.sample.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import id.herald.sample.ui.cart.CartScreen
import id.herald.sample.ui.detail.DetailScreen
import id.herald.sample.ui.home.HomeScreen
import id.herald.sample.ui.navigation.model.BottomBarScreen
import id.herald.sample.ui.navigation.model.GeneralScreen
import id.herald.sample.ui.profile.ProfileScreen
import id.herald.sample.ui.search.SearchScreen

/** Created by Herald Santos on 04/12/2024. */

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = BottomBarScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomBarScreen.Home.route) {
                HomeScreen(
                    animatedContentScope = this,
                    navigateToDetail = { product ->
                        navController.navigate(
                            GeneralScreen.DetailProduct.createRoute(
                                product.id,
                                BottomBarScreen.Home.route
                            )
                        )
                        //navController.navigate(GeneralScreen.Detail2Product.createRoute(Uri.encode(toJson(product))))
                    },
                    navigateToSearch = {
                        navController.navigate(GeneralScreen.SearchProduct.route)
                    }
                )
            }
            composable(BottomBarScreen.Cart.route) {
                CartScreen(
                    animatedContentScope = this,
                    navigateToDetail = { productId ->
                        navController.navigate(
                            GeneralScreen.DetailProduct.createRoute(
                                productId,
                                BottomBarScreen.Cart.route
                            )
                        )
                    }
                )
            }
            composable(BottomBarScreen.Profile.route) { ProfileScreen() }
            composable(
                route = GeneralScreen.DetailProduct.route,
                arguments = listOf(navArgument("productId") { type = NavType.StringType }),
            ) {
                val productId = it.arguments?.getString("productId").orEmpty()
                val screen = it.arguments?.getString("screen").orEmpty()
                DetailScreen(
                    animatedContentScope = this,
                    productId,
                    screen,
                    navigateBack = { navController.navigateUp() }
                )
            }
            composable(
                route = GeneralScreen.SearchProduct.route,
            ) {
                SearchScreen(
                    animatedContentScope = this,
                    navigateToDetail = { productId ->
                        navController.navigate(
                            GeneralScreen.DetailProduct.createRoute(
                                productId,
                                BottomBarScreen.Home.route
                            )
                        )
                    },
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}