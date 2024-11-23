package kz.nfactorial.nfactorialapp.home.presentation

import kz.nfactorial.nfactorialapp.home.presentation.models.AccountInfo
import kz.nfactorial.nfactorialapp.home.presentation.models.Banner
import kz.nfactorial.nfactorialapp.home.presentation.models.ChipItem
import kz.nfactorial.nfactorialapp.home.presentation.models.Collection
import kz.nfactorial.nfactorialapp.home.presentation.models.Store

data class HomeState(
    val account: AccountInfo,
    val searchField: String,
    val banner: Banner,
    val filters: List<ChipItem>,
    val selectedFilterIds: Set<Int>,
    val collections: List<Collection>,
    val stores: List<Store>,
)