package kz.nfactorial.nfactorialapp.home.presentation

import android.app.Activity
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import kz.nfactorial.nfactorialapp.R
import kz.nfactorial.nfactorialapp.databinding.ItemFilterChipBinding
import kz.nfactorial.nfactorialapp.home.presentation.models.AccountInfo
import kz.nfactorial.nfactorialapp.home.presentation.models.Banner
import kz.nfactorial.nfactorialapp.home.presentation.models.ChipItem
import kz.nfactorial.nfactorialapp.home.presentation.models.Collection
import kz.nfactorial.nfactorialapp.home.presentation.models.StoreUI
import kz.nfactorial.nfactorialapp.home.presentation.models.displayText
import kz.nfactorial.nfactorialapp.photo.presentation.ChoosePhotoActivity
import kz.nfactorial.nfactorialapp.ui.theme.LocalColors
import kz.nfactorial.nfactorialapp.ui.theme.LocalTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    effect: HomeEffect?,
) {
    val activity = LocalContext.current as Activity
    val uiData = state.uiData
    val adBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isAdBottomSheetVisible by remember { mutableStateOf(false) }
    val openChooseImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onEvent(HomeEvent.OnImageUpdated)
        }
    }
    if (uiData != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding(),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item(key = "Header") { Header(uiData.account, onEvent) }
                item(key = "SearchBar") {
                    SearchBar(
                        text = state.searchField,
                        modifier = Modifier.padding(top = 16.dp),
                        onEvent = onEvent,
                    )
                }
//                item(key = "BannerItem") {
//                    BannerItem(
//                        banner = uiData.banner,
//                        onEvent = onEvent,
//                        modifier = Modifier.padding(top = 16.dp),
//                    )
//                }
                item(key = "FilterChips") {
                    FilterChips(
                        filters = uiData.filters,
                        selectedIds = state.selectedFilterIds,
                        modifier = Modifier.padding(top = 16.dp),
                        onEvent = onEvent,
                    )
                }
//                items(uiData.collections, key = { it.name }) { collection ->
//                    ProductCollections(
//                        collection = collection,
//                        modifier = Modifier.padding(top = 16.dp),
//                    )
//                }
                item(key = "Stores") {
                    Stores(
                        storeUIS = uiData.storeUI,
                        onEvent = onEvent,
                        modifier = Modifier.padding(top = 16.dp),
                    )
                }
            }

            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                visible = state.connectionLostBannerVisible,
                enter = slideInVertically(),
                exit = slideOutVertically(),
            ) {
                ConnectionLostComponent()
            }
        }
    }

    LaunchedEffect(state.isAdShow) {
        if (state.isAdShow) {
            isAdBottomSheetVisible = true
        } else {
            launch {
                adBottomSheetState.hide()
            }.invokeOnCompletion {
                isAdBottomSheetVisible = false
            }
        }
    }

    if (isAdBottomSheetVisible) {
        ModalBottomSheet(
            dragHandle = null,
            sheetState = adBottomSheetState,
            contentColor = LocalColors.current.back.primary,
            containerColor = LocalColors.current.back.primary,
            onDismissRequest = { onEvent(HomeEvent.OnAdCloseClick) }
        ) {
            AdvertisementContent(onEvent)
        }
    }

    val context = LocalContext.current
    val connectivityManager = remember {
        getSystemService(context, ConnectivityManager::class.java) as ConnectivityManager
    }
    DisposableEffect(Unit) {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                onEvent(HomeEvent.OnConnectionAvailable)
            }

            override fun onLost(network: Network) {
                onEvent(HomeEvent.OnConnectionLost)
            }
        }

        connectivityManager.registerDefaultNetworkCallback(callback)
        onDispose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
    LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
        onEvent(HomeEvent.OnCreate)
    }

    LaunchedEffect(effect) {
        when (effect) {
            is HomeEffect.OpenChooseImage -> {
                val intent = Intent(activity, ChoosePhotoActivity::class.java)
                openChooseImageLauncher.launch(intent)
            }
            null -> Unit
        }
    }
}

@Composable
private fun Header(
    accountInfo: AccountInfo?,
    onEvent: (HomeEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .height(48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(),
                    onClick = { onEvent(HomeEvent.OnRegistrationClick) },
                )
        ) {
            Text(
                text = stringResource(R.string.home_header_good_morning),
                style = LocalTypography.current.medium.medium300,
            )
            Text(
                text = accountInfo?.fullName ?: stringResource(R.string.update_your_profile),
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
                .clip(iconShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(),
                    onClick = { onEvent(HomeEvent.OnProfileImageClick) }
                ),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                modifier = Modifier.size(24.dp),
                model = accountInfo?.image ?: R.drawable.baseline_person_24,
                contentDescription = "profile",
            )
        }
    }
}

@Composable
private fun SearchBar(
    text: String,
    modifier: Modifier = Modifier,
    onEvent: (HomeEvent) -> Unit,
) {
    Box(
        modifier = modifier
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .height(53.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize()
                .clip(remember { RoundedCornerShape(8.dp) }),
            value = text,
            onValueChange = { onEvent(HomeEvent.OnSearchChanged(it)) },
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
private fun BannerItem(
    banner: Banner,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(remember { RoundedCornerShape(20.dp) })
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = { onEvent(HomeEvent.OnAdClick) }
            ),
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = banner.image,
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
                text = banner.title,
                color = LocalColors.current.text.primaryInverse,
                style = LocalTypography.current.bold.bold700,
            )
            Text(
                text = banner.description,
                color = LocalColors.current.text.primaryInverse,
                style = LocalTypography.current.medium.medium400,
            )
        }
    }
}

@Composable
private fun ConnectionLostComponent() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(32.dp)
        .background(LocalColors.current.element.destructive)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            color = LocalColors.current.text.primaryInverse,
            style = LocalTypography.current.medium.medium300,
            text = "Connection Lost",
        )
    }

    SideEffect {
        Log.d("HomeScreen", "ConnectionLostComponent shown")
    }
}

@Composable
private fun AdvertisementContent(onEvent: (HomeEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = { onEvent(HomeEvent.OnAdCloseClick) }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_close_24),
                    contentDescription = "close",
                    tint = LocalColors.current.icon.primary,
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.advertisement_title),
                color = LocalColors.current.text.primary,
                style = LocalTypography.current.semibold.semibold500,
            )
        }
        Image(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            painter = painterResource(R.drawable.nike_banner),
            contentDescription = "banner",
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = "NIKE 50% OFF: Your Perfect Sneakers at Half the Price!",
            color = LocalColors.current.text.primary,
            style = LocalTypography.current.bold.bold500,
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "It’s time to upgrade your wardrobe and step into a stylish future with Nike! Right now, enjoy a 50% discount on the entire range of sneakers. Choose your perfect pair for sports, casual outings, or bold everyday looks. Hurry up—this offer is for a limited time only! ",
            color = LocalColors.current.text.primary,
            style = LocalTypography.current.semibold.semibold400,
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onEvent(HomeEvent.OnAdCloseClick) },
            colors = ButtonDefaults.buttonColors(
                containerColor = LocalColors.current.brand.primary
            )
        ) {
            Text(
                text = "Okay!",
                color = LocalColors.current.text.primaryInverse,
                style = LocalTypography.current.medium.medium500,
            )
        }
    }
}

@Composable
private fun FilterChips(
    filters: List<ChipItem>,
    selectedIds: Set<Int>,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 30.dp)
    ) {
        items(filters) { item ->
            FilterChip(
                selected = item.id in selectedIds,
                onClick = { onEvent(HomeEvent.OnFilterClick(item)) },
                label = {
                    Text(
                        text = item.name,
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
private fun ProductCollections(
    collection: Collection,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = collection.name,
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
            items(collection.products) { product ->
                Box(
                    modifier = Modifier
                        .size(120.dp, 160.dp)
                        .clip(remember { RoundedCornerShape(12.dp) })
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(product.image),
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
    storeUIS: List<StoreUI>,
    modifier: Modifier = Modifier,
    onEvent: (HomeEvent) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
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
            items(storeUIS) { store ->
                Box(
                    modifier = Modifier
                        .size(150.dp, 109.dp)
                        .clip(remember { RoundedCornerShape(12.dp) })
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(),
                            onClick = { onEvent(HomeEvent.OnStoreClick(store)) }
                        )
                ) {
                    AsyncImage(
                        model = store.image,
                        contentDescription = store.name,
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
