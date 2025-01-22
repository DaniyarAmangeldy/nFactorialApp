package kz.nfactorial.nfactorialapp.store.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import kz.nfactorial.nfactorialapp.R
import kz.nfactorial.nfactorialapp.extensions.IntExtensions.toShortString
import kz.nfactorial.nfactorialapp.home.presentation.models.Product
import kz.nfactorial.nfactorialapp.home.presentation.models.StoreUI
import kz.nfactorial.nfactorialapp.home.presentation.models.displayText
import kz.nfactorial.nfactorialapp.ui.theme.LocalColors
import kz.nfactorial.nfactorialapp.ui.theme.LocalTypography

@Composable
fun StoreScreen(storeUI: StoreUI, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
    ) {
        Header(onBackClick)
        LazyColumn(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxSize()
                .safeDrawingPadding(),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item(key = "StoreInfo") {
                StoreInfo(storeUI)
            }
            item(key = "Products") {
                val products = remember {
                    (storeUI.products + storeUI.products + storeUI.products + storeUI.products)
                }
                ProductsGrid(products, modifier = Modifier.padding(top = 24.dp))
            }
        }
    }

}

@Composable
private fun Header(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(56.dp),
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clip(CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(),
                    onClick = onBackClick,
                ),
            painter = painterResource(R.drawable.ic_back),
            contentDescription = "back"
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Store Details",
            style = LocalTypography.current.bold.bold700,
        )
    }
}

@Composable
fun StoreInfo(storeUI: StoreUI, modifier: Modifier = Modifier) {
    Row(
       modifier = modifier
           .padding(horizontal = 16.dp)
           .fillMaxWidth()
           .height(80.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(70.dp, 80.dp)
                .clip(remember { RoundedCornerShape(12.dp) })
        ) {
            Image(
                painter = rememberAsyncImagePainter(storeUI.image),
                contentDescription = storeUI.name,
                contentScale = ContentScale.Inside,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x1d000000))
            )
        }
        Column(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = storeUI.name,
                color = LocalColors.current.text.primary,
                style = LocalTypography.current.bold.bold500,
            )
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_location),
                    contentDescription = null,
                )
                Text(
                    text = storeUI.name,
                    color = LocalColors.current.text.primary,
                    style = LocalTypography.current.medium.medium300,
                )
            }
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.star),
                    contentDescription = null,
                    tint = LocalColors.current.element.star,
                )
                Text(
                    text = stringResource(
                        R.string.store_details_rating_fmt,
                        storeUI.rating.average,
                        storeUI.rating.count.toShortString()
                    ),
                    color = LocalColors.current.text.primary,
                    style = LocalTypography.current.medium.medium300,
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProductsGrid(products: List<Product>, modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        products.forEach { product ->
            Box(
                modifier = Modifier
                    .size(140.dp, 130.dp)
                    .clip(remember { RoundedCornerShape(12.dp) })
            ) {
                Image(
                    painter = rememberAsyncImagePainter(product.image),
                    contentDescription = product.name,
                    contentScale = ContentScale.FillWidth,
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x1d000000))
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 12.dp, bottom = 12.dp, end = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        modifier = Modifier.weight(2f),
                        text = product.name,
                        color = LocalColors.current.text.primaryInverse,
                        style = LocalTypography.current.semibold.semibold400,
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = product.price.displayText,
                        color = LocalColors.current.text.primaryInverse,
                        style = LocalTypography.current.bold.bold400,
                    )
                }
            }
        }
    }
}