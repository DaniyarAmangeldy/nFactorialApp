package kz.nfactorial.nfactorialapp.search.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import kz.nfactorial.nfactorialapp.R
import kz.nfactorial.nfactorialapp.home.presentation.models.Product
import kz.nfactorial.nfactorialapp.home.presentation.models.displayText
import kz.nfactorial.nfactorialapp.ui.theme.LocalColors
import kz.nfactorial.nfactorialapp.ui.theme.LocalTypography

@Composable
fun SearchScreen(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Header()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item(key = "SearchBar") {
                SearchBar(
                    text = state.searchField,
                    modifier = Modifier.padding(top = 16.dp),
                    onEvent = onEvent,
                )
            }
            when (val uiState = state.uiState) {
                is SearchState.UiState.Data -> {
                    items(uiState.items) { product ->
                        ProductItem(product)
                    }
                }
                SearchState.UiState.Loading -> {
                    item("Loader") {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                                color = LocalColors.current.brand.primary
                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
private fun Header() {
    Box(
        modifier = Modifier.fillMaxWidth().height(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = stringResource(R.string.search_title),
            color = LocalColors.current.text.primary,
            style = LocalTypography.current.bold.bold500,
        )
    }
}

@Composable
private fun SearchBar(
    text: String,
    modifier: Modifier = Modifier,
    onEvent: (SearchEvent) -> Unit,
) {
    Box(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            .height(53.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize()
                .clip(remember { RoundedCornerShape(8.dp) }),
            value = text,
            onValueChange = { onEvent(SearchEvent.OnSearchChanged(it)) },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LocalColors.current.back.base,
                focusedContainerColor = LocalColors.current.back.base,
                unfocusedTextColor = LocalColors.current.text.primary,
                focusedTextColor = LocalColors.current.text.primary,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            textStyle = LocalTypography.current.medium.medium400,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "search_icon",
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.home_search_placeholder),
                    color = LocalColors.current.text.secondary,
                    style = LocalTypography.current.medium.medium300,
                )
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        )
    }
}

@Composable
private fun ProductItem(product: Product) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(140.dp, 130.dp)
                .clip(remember { RoundedCornerShape(12.dp) })
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x1d000000))
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = product.name,
                color = LocalColors.current.text.primary,
                style = LocalTypography.current.semibold.semibold400,
            )
            Text(
                text = product.price.displayText,
                color = LocalColors.current.text.secondary,
                style = LocalTypography.current.semibold.semibold400,
            )
        }
    }
}