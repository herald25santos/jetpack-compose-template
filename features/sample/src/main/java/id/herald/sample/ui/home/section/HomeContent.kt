package id.herald.sample.ui.home.section

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.herald.core.data.model.Product
import id.herald.sample.ui.component.EmptyProduct
import id.herald.sample.ui.component.ProductItem

/** Created by Herald Santos on 04/12/2024. */

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeContent(
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier,
    listProduct: MutableList<Product>?,
    navigateToDetail: (Product) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (listProduct != null) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(140.dp),
                content = {
                    items(listProduct, key = { it.id ?: -1 }) { product ->
                        ProductItem(
                            animatedContentScope = animatedContentScope,
                            product = product,
                            modifier = modifier
                                .fillMaxWidth()
                                .animateItem(tween(durationMillis = 100))
                                .clickable { navigateToDetail(product) }
                        )
                    }
                }, contentPadding = PaddingValues(8.dp)
            )
            if (listProduct.isEmpty()) EmptyProduct()
        } else EmptyProduct()
    }
}