package kz.nfactorial.nfactorialapp.home.domain.entity

sealed interface HomeItem {

    data class Collection(
        val stores: List<Store>,
    ): HomeItem

    data class Filters(
        val filters: List<Filter>
    ): HomeItem
}