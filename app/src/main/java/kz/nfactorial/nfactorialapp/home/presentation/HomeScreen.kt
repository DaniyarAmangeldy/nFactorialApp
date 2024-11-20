package kz.nfactorial.nfactorialapp.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kz.nfactorial.nfactorialapp.R
import kz.nfactorial.nfactorialapp.extensions.CollectionExtensions.addOrRemove
import kz.nfactorial.nfactorialapp.home.presentation.models.ChipItem
import kz.nfactorial.nfactorialapp.home.presentation.models.Collection
import kz.nfactorial.nfactorialapp.home.presentation.models.Price
import kz.nfactorial.nfactorialapp.home.presentation.models.Product
import kz.nfactorial.nfactorialapp.home.presentation.models.Rating
import kz.nfactorial.nfactorialapp.home.presentation.models.Store
import kz.nfactorial.nfactorialapp.home.presentation.models.displayText
import kz.nfactorial.nfactorialapp.ui.theme.LocalColors
import kz.nfactorial.nfactorialapp.ui.theme.LocalTypography

@Composable
fun HomeScreen(onStoreClick: (Store) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item(key = "Header") { Header() }
        item(key = "SearchBar") {
            SearchBar(
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        item(key = "BannerItem") {
            BannerItem(modifier = Modifier.padding(top = 16.dp))
        }
        item(key = "FilterChips") {
            FilterChips(modifier = Modifier.padding(top = 16.dp))
        }
        item(key = "Products") {
            ProductCollections(modifier = Modifier.padding(top = 16.dp))
        }
        item(key = "Stores") {
            Stores(
                modifier = Modifier.padding(top = 16.dp),
                onClick = onStoreClick,
            )
        }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .height(48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(
                text = stringResource(R.string.home_header_good_morning),
                style = LocalTypography.current.medium.medium300,
            )
            Text(
                text = "Daniyar Amangeldy",
                style = LocalTypography.current.semibold.semibold400,
            )
        }
        val iconShape = remember { RoundedCornerShape(12.dp) }
        Box(
            modifier = Modifier
                .size(38.dp)
                .border(
                    width = 1.dp,
                    color = LocalColors.current.element.light,
                    shape = iconShape,
                )
                .clip(iconShape),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.ic_spiderman),
                contentDescription = "profile",
            )
        }
    }
}

@Composable
private fun SearchBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .height(53.dp)
    ) {
        var value by remember { mutableStateOf("") }
        TextField(
            modifier = Modifier
                .fillMaxSize()
                .clip(remember { RoundedCornerShape(8.dp) }),
            value = value,
            onValueChange = { value = it },
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
private fun BannerItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(remember { RoundedCornerShape(20.dp) }),
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.nike_banner),
            contentDescription = "banner_image",
            contentScale = ContentScale.FillWidth,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x1d000000))
        )
        Column(
            modifier = Modifier
                .padding(top = 28.dp, start = 20.dp)
                .fillMaxHeight()
                .align(Alignment.TopStart),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "It's Time for\nPayday Sale!",
                color = LocalColors.current.text.primaryInverse,
                style = LocalTypography.current.bold.bold700,
            )
            Text(
                text = "Up to 70% off!",
                color = LocalColors.current.text.primaryInverse,
                style = LocalTypography.current.medium.medium400,
            )
        }
    }
}

@Composable
private fun FilterChips(modifier: Modifier = Modifier) {
    val filters = remember {
        listOf(
            ChipItem(1, "Sandals"),
            ChipItem(2, "Heels"),
            ChipItem(3, "Shoes"),
            ChipItem(4, "Slippers"),
            ChipItem(5, "Boots"),
            ChipItem(6, "Sneakers"),
            ChipItem(7, "Loafers"),
            ChipItem(8, "Oxfords"),
            ChipItem(9, "Moccasins"),
            ChipItem(10, "Flip-flops"),
        )
    }
    var selectedIds by remember { mutableStateOf<Set<Int>>(emptySet()) }
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 30.dp)
    ) {
        items(filters) { (id, name) ->
            FilterChip(
                selected = id in selectedIds,
                onClick = { selectedIds = selectedIds.addOrRemove(id) },
                label = {
                    Text(
                        text = name,
                        style = LocalTypography.current.medium.medium300,
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = LocalColors.current.brand.primary,
                    containerColor = LocalColors.current.back.base,
                    labelColor = LocalColors.current.text.secondary,
                    selectedLabelColor = LocalColors.current.text.primaryInverse,
                ),
                border = null,
            )
        }
    }
}

@Composable
private fun ProductCollections(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        val collections = remember {
            Collection(
                products = listOf(
                    Product(
                        name = "Founds",
                        id = 1,
                        image = R.drawable.founds,
                        price = Price(45, "$"),
                    ),
                    Product(
                        name = "Demix",
                        id = 2,
                        image = R.drawable.demix,
                        price = Price(40, "$"),
                    ),
                    Product(
                        name = "Dino Ricci",
                        id = 3,
                        image = R.drawable.dino_ricci,
                        price = Price(38, "$"),
                    ),
                    Product(
                        name = "Adidas Originals",
                        id = 4,
                        image = R.drawable.adidas_originals,
                        price = Price(100, "$"),
                    )
                )
            )
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Trending Products",
                color = LocalColors.current.text.primary,
                style = LocalTypography.current.bold.bold600,
            )
            Text(
                text = stringResource(R.string.action_see_all),
                color = LocalColors.current.text.primary,
                style = LocalTypography.current.medium.medium300,
            )
        }
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 30.dp)
        ) {
            items(collections.products) { product ->
                Box(
                    modifier = Modifier
                        .size(120.dp, 160.dp)
                        .clip(remember { RoundedCornerShape(12.dp) })
                ) {
                    Image(
                        painter = painterResource(product.image),
                        contentDescription = product.name,
                        contentScale = ContentScale.FillBounds,
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0x1d000000))
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 12.dp, top = 12.dp, end = 12.dp)
                    ) {
                        Text(
                            text = product.name,
                            color = LocalColors.current.text.primaryInverse,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = LocalTypography.current.semibold.semibold400,
                        )
                        Text(
                            text = product.price.displayText,
                            color = LocalColors.current.text.primaryInverse,
                            style = LocalTypography.current.bold.bold400,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Stores(
    modifier: Modifier = Modifier,
    onClick: (Store) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        val stores = remember {
            listOf(
                Store(
                    id = 0,
                    name = "Lamoda",
                    image = R.drawable.store_lamoda,
                    rating = Rating(4.7f, 2300),
                ),
                Store(
                    id = 1,
                    name = "Zara",
                    image = R.drawable.store_zara,
                    rating = Rating(4.4f, 21200),
                    products = listOf(
                        Product(
                            name = "Founds",
                            id = 1,
                            image = R.drawable.shoes_1,
                            price = Price(45, "$"),
                        ),
                        Product(
                            name = "Demix",
                            id = 2,
                            image = R.drawable.shoes_5,
                            price = Price(40, "$"),
                        ),
                        Product(
                            name = "Dino Ricci",
                            id = 3,
                            image = R.drawable.shoes_6,
                            price = Price(38, "$"),
                        ),
                        Product(
                            name = "Adidas Originals",
                            id = 4,
                            image = R.drawable.shoes_7,
                            price = Price(100, "$"),
                        ),
                    )
                ),
                Store(
                    id = 2,
                    name = "Intertop",
                    image = R.drawable.store_intertop,
                    rating = Rating(3.4f, 500),
                ),
                Store(
                    id = 3,
                    name = "Adidas",
                    image = R.drawable.store_adidas,
                    rating = Rating(5.0f, 23000),
                )
            )
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Top Stores",
                color = LocalColors.current.text.primary,
                style = LocalTypography.current.bold.bold600,
            )
            Text(
                text = stringResource(R.string.action_see_all),
                color = LocalColors.current.text.primary,
                style = LocalTypography.current.medium.medium300,
            )
        }
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 30.dp)
        ) {
            items(stores) { store ->
                Box(
                    modifier = Modifier
                        .size(150.dp, 109.dp)
                        .clip(remember { RoundedCornerShape(12.dp) })
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(),
                            onClick = { onClick(store) }
                        )
                ) {
                    Image(
                        painter = painterResource(store.image),
                        contentDescription = store.name,
                        contentScale = ContentScale.Inside,
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0x1d000000))
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 12.dp, bottom = 12.dp, end = 12.dp)
                    ) {
                        Text(
                            text = store.name,
                            color = LocalColors.current.text.primaryInverse,
                            style = LocalTypography.current.bold.bold400,
                        )
                    }
                }
            }
        }
    }
}
