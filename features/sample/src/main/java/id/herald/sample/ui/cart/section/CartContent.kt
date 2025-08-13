package id.herald.sample.ui.cart.section

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import id.herald.core.R
import id.herald.core.data.datasource.local.db.entity.ProductEntity
import id.herald.core.util.Extensions.myToast
import id.herald.sample.ui.cart.CartViewModel
import id.herald.sample.ui.component.EmptyProduct
import id.herald.sample.ui.component.ProductCartItem

/** Created by Herald Santos on 04/12/2024. */

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CartContent(
    animatedContentScope: AnimatedContentScope,
    products: MutableList<ProductEntity>,
    navigateToDetail: (Int) -> Unit,
    viewModel: CartViewModel
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            items(products, key = { it.id ?: -1 }) { product ->
                val strRemoveCart = stringResource(id = R.string.remove_from_cart_, product.title.toString())
                ProductCartItem(
                    animatedContentScope = animatedContentScope,
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(tween(durationMillis = 100))
                        .clickable {
                            navigateToDetail(product.id ?: return@clickable)
                        },
                    product = product,
                    onRemoveClicked = {
                        viewModel.deleteProductDb(product)
                        context.myToast(strRemoveCart)
                    }
                )
            }
        }, contentPadding = PaddingValues(8.dp)
    )
    if (products.isEmpty()) EmptyProduct()
}