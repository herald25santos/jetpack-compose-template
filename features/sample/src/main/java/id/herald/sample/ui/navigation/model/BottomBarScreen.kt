package id.herald.sample.ui.navigation.model

import id.herald.core.R

/** Created by Herald Santos on 04/12/2024. */

sealed class BottomBarScreen(val route: String) {
    data object Home : BottomBar(
        route = "home",
        titleResId = R.string.home,
        icon = R.drawable.home,
        iconFocused = R.drawable.home
    )

    data object Cart : BottomBar(
        route = "cart",
        titleResId = R.string.cart,
        icon = R.drawable.cart,
        iconFocused = R.drawable.cart
    )

    data object Profile : BottomBar(
        route = "profile",
        titleResId = R.string.profile,
        icon = R.drawable.user,
        iconFocused = R.drawable.user
    )
}