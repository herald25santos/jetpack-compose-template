package id.herald.sample.ui.detail.section

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.herald.core.R
import id.herald.core.data.model.Product
import id.herald.core.util.UtilFunctions.fromDollarToPhp
import id.herald.sample.ui.navigation.model.BottomBarScreen

/** Created by Herald Santos on 04/12/2024. */

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TitleProduct(
    animatedContentScope: AnimatedContentScope,
    product: Product,
    screen: String
) {
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        Text(
            text = product.title ?: stringResource(R.string.dash),
            maxLines = 2,
            lineHeight = 25.sp,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal, fontSize = 28.sp
            ),
            color = Color.Black,
            modifier = Modifier.sharedElement(
                state = rememberSharedContentState(
                    key = if (screen == BottomBarScreen.Home.route) "text/${product.title}"
                    else "cart/text/${product.title}"
                ),
                animatedVisibilityScope = animatedContentScope,
                boundsTransform = { _, _ ->
                    tween(durationMillis = 1000)
                }
            )
        )
        Text(
            text = product.price.fromDollarToPhp(),
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Light, fontSize = 20.sp
            ),
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.sharedElement(
                state = rememberSharedContentState(
                    key = if (screen == BottomBarScreen.Home.route) "cart/text/${product.price.fromDollarToPhp()}"
                    else "text/${product.price.fromDollarToPhp()}"
                ),
                animatedVisibilityScope = animatedContentScope,
                boundsTransform = { _, _ ->
                    tween(durationMillis = 1000)
                }
            )
        )
    }
}