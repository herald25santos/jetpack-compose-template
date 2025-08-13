package id.herald.sample.ui.search

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.herald.core.R
import id.herald.core.data.UiState
import id.herald.core.data.model.ProductResponse
import id.herald.core.ui.component.molecules.SearchBar
import id.herald.core.ui.template.MainTemplate
import id.herald.core.ui.theme.Gray200
import id.herald.sample.ui.component.ProgressProduct
import id.herald.sample.ui.home.HomeViewModel
import id.herald.sample.ui.search.section.SearchContent

/** Created by Herald Santos on 04/12/2024. */

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SearchScreen(
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    navigateBack: () -> Unit,
) {
    val query by viewModel.query
    val focusRequester = remember { FocusRequester() }
    val uiStateProduct by remember { viewModel.uiStateProduct }.collectAsState()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    MainTemplate(
        modifier = modifier,
        topBar = {
            Row(
                modifier = modifier.background(MaterialTheme.colorScheme.primary),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = navigateBack,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::searchProductApiCall,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .focusRequester(focusRequester),
                )
            }
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
                        SearchContent(
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
        }
    )
}