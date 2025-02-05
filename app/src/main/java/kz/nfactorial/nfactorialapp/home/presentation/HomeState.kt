package kz.nfactorial.nfactorialapp.home.presentation

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import kz.nfactorial.nfactorialapp.home.presentation.models.AccountInfo
import kz.nfactorial.nfactorialapp.home.presentation.models.ChipItem
import kz.nfactorial.nfactorialapp.home.presentation.models.StoreUI

data class HomeState(
    val searchField: String,
    val selectedFilterIds: Set<Int>,
    val isAdShow: Boolean = false,
    val uiData: UiData? = null,
    val player: Player,
    val connectionLostBannerVisible: Boolean = false,
) {

    data class UiData(
        val account: AccountInfo?,
//        val banner: Banner,
        val filters: List<ChipItem>,
//        val collections: List<Collection>,
        val storeUI: List<StoreUI>,
        val mediaItem: MediaItem,
    )
}

sealed interface HomeUiItems {

    class Header: HomeUiItems

    class SearchBar: HomeUiItems

    class Banner: HomeUiItems

    class Filters: HomeUiItems
}