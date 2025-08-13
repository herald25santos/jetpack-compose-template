package id.herald.sample.ui.detail.section

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.herald.core.R
import id.herald.core.data.UiState
import id.herald.core.data.model.Product
import id.herald.core.ui.theme.Gray200
import id.herald.core.util.Dimens
import id.herald.core.util.Extensions.myToast
import id.herald.core.util.UtilFunctions.logE
import id.herald.sample.ui.detail.DetailViewModel

/** Created by Herald Santos on 04/12/2024. */

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailContent(
    animatedContentScope: AnimatedContentScope,
    product: Product,
    screen: String,
    viewModel: DetailViewModel
) {
    val context = LocalContext.current
    val strBuy = stringResource(R.string.buy)
    val strAddedCart = stringResource(R.string.added_to_cart)
    val strThanks = stringResource(R.string.thank_you_buy)
    val productId = product.id?.toLong() ?: -1
    var buyText by remember { mutableStateOf(strBuy) }
    var isAlreadyOnCart by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(color = Color.White),
    ) {
        item {
            ImageProductPager(animatedContentScope = animatedContentScope, product = product, screen = screen)
            Spacer(modifier = Modifier.size(Dimens.dp8))
            TitleProduct(animatedContentScope = animatedContentScope, product = product, screen = screen)
            Divider(color = Gray200, thickness = 10.dp)
            DescriptionProduct(product = product)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.BottomStart
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            viewModel.uiStateDbProduct.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        viewModel.getProductByIdDb(productId)
                    }

                    is UiState.Success -> {
                        isAlreadyOnCart = true
                    }

                    is UiState.Error -> {
                        logE(uiState.errorMessage)
                    }
                }
            }
            if (!isAlreadyOnCart) {
                Text(
                    text = stringResource(R.string.add_to_cart),
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondary)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 20.dp)
                        .clickable {
                            context.myToast(strAddedCart)
                            viewModel.insertProductDb(product)
                        },
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            }
            Text(
                text = buyText,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 20.dp)
                    .clickable {
                        context.myToast(strThanks)
                        buyText = strThanks
                    },
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
    }
}