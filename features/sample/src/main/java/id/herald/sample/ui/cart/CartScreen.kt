package id.herald.sample.ui.cart

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import id.herald.core.R
import id.herald.core.data.UiState
import id.herald.core.ui.theme.Gray200
import id.herald.sample.ui.cart.section.CartContent
import id.herald.sample.ui.component.ProgressProduct

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CartScreen(
    animatedContentScope: AnimatedContentScope,
    viewModel: CartViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.cart))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
            )
        }, content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray200)
                    .padding(it)
            ) {
                viewModel.uiStateDbProducts.collectAsState(initial = UiState.Loading).value.let { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            viewModel.getProductsDb()
                            ProgressProduct()
                        }

                        is UiState.Success -> {
                            CartContent(
                                animatedContentScope = animatedContentScope,
                                products = uiState.data,
                                viewModel = viewModel,
                                navigateToDetail = navigateToDetail
                            )
                        }

                        is UiState.Error -> {
                            Text(
                                text = stringResource(R.string.error_product),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        })

}
