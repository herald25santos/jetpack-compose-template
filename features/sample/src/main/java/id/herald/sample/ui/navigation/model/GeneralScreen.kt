package id.herald.sample.ui.navigation.model

/** Created by Herald Santos on 04/12/2024. */

sealed class GeneralScreen(val route: String) {

    data object DetailProduct : BottomBarScreen("home/{productId}/{screen}") {
        fun createRoute(productId: Int?, screen: String) = "home/$productId/$screen"
    }

    data object Detail2Product : BottomBarScreen("product-detail/{product}") {
        fun createRoute(product: String?) = "product-detail/$product"
    }

    data object SearchProduct : GeneralScreen(route = "home/search")
}