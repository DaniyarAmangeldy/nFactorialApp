package kz.nfactorial.nfactorialapp.home.presentation

import kz.nfactorial.nfactorialapp.home.presentation.models.AccountInfo
import kz.nfactorial.nfactorialapp.home.presentation.models.Banner
import kz.nfactorial.nfactorialapp.home.presentation.models.ChipItem
import kz.nfactorial.nfactorialapp.home.presentation.models.Collection
import kz.nfactorial.nfactorialapp.home.presentation.models.Store

data class HomeState(
    val searchField: String,
    val selectedFilterIds: Set<Int>,
    val isAdShow: Boolean = false,
    val uiData: UiData? = null,
    val connectionLostBannerVisible: Boolean = false,
) {

    data class UiData(
        val account: AccountInfo?,
        val banner: Banner,
        val filters: List<ChipItem>,
        val collections: List<Collection>,
        val stores: List<Store>,
    )
}