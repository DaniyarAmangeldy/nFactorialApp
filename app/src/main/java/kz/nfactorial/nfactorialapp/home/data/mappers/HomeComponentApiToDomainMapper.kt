package kz.nfactorial.nfactorialapp.home.data.mappers

import kz.nfactorial.nfactorialapp.home.data.model.FilterApi
import kz.nfactorial.nfactorialapp.home.data.model.HomeComponentApi
import kz.nfactorial.nfactorialapp.home.data.model.StoreApi
import kz.nfactorial.nfactorialapp.home.domain.entity.Filter
import kz.nfactorial.nfactorialapp.home.domain.entity.HomeItem
import kz.nfactorial.nfactorialapp.home.domain.entity.Store

class HomeComponentApiToDomainMapper {

    operator fun invoke(apiModel: HomeComponentApi): List<HomeItem> {
        return listOf(
            HomeItem.Collection(apiModel.storeCollections.flatMap { it.stores }.map(::toDomain)),
            HomeItem.Filters(apiModel.filters.map(::toDomain)),
        )
    }

    private fun toDomain(filterApi: FilterApi): Filter {
        return Filter(
            id = filterApi.id,
            name = filterApi.name,
        )
    }

    private fun toDomain(apiStore: StoreApi): Store {
        return Store(
            id = apiStore.id,
            name = apiStore.name,
            image = apiStore.image,
        )
    }
}