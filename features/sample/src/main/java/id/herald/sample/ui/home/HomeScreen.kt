package id.herald.sample.ui.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import id.herald.core.R
import id.herald.core.data.UiState
import id.herald.core.data.model.Product
import id.herald.core.data.model.ProductResponse
import id.herald.core.ui.component.molecules.SearchBar
import id.herald.core.ui.template.MainTemplate
import id.herald.core.ui.theme.Gray200
import id.herald.sample.ui.component.ProgressProduct
import id.herald.sample.ui.home.section.HomeContent


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Product) -> Unit,
    navigateToSearch: () -> Unit,
) {
    val uiStateProduct by remember { viewModel.uiStateProduct }.collectAsState()

    MainTemplate(
        modifier = modifier,
        topBar = {
            SearchBar(
                query = "",
                onQueryChange = {},
                modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                isEnabled = false,
                onSearchClicked = { navigateToSearch() }
            )
        },
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
                    .background(Gray200)
            ) {
                when (uiStateProduct) {
                    is UiState.Loading -> {
                        viewModel.getProductsApiCall()
                        ProgressProduct()
                    }

                    is UiState.Success -> {
                        HomeContent(
                            animatedContentScope = animatedContentScope,
                            modifier = modifier,
                            listProduct = (uiStateProduct as UiState.Success<ProductResponse>).data.products,
                            navigateToDetail = navigateToDetail,
                        )
                    }

                    is UiState.Error -> {
                        Text(text = stringResource(R.string.error_product), color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        })
}