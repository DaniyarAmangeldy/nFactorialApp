package kz.nfactorial.nfactorialapp.home.presentation.factory

import kz.nfactorial.nfactorialapp.home.data.model.ProductApi
import kz.nfactorial.nfactorialapp.home.domain.entity.HomeItem
import kz.nfactorial.nfactorialapp.home.domain.entity.Store
import kz.nfactorial.nfactorialapp.home.presentation.HomeState
import kz.nfactorial.nfactorialapp.home.presentation.models.AccountInfo
import kz.nfactorial.nfactorialapp.home.presentation.models.ChipItem
import kz.nfactorial.nfactorialapp.home.presentation.models.Price
import kz.nfactorial.nfactorialapp.home.presentation.models.Product
import kz.nfactorial.nfactorialapp.home.presentation.models.Rating
import kz.nfactorial.nfactorialapp.home.presentation.models.StoreUI
import kz.nfactorial.nfactorialapp.registration.presentation.model.Account

class HomeStateFactory {

    fun createInitialState(): HomeState {
        return HomeState(
            searchField = "",
            uiData = null,
            isAdShow = true,
            selectedFilterIds = emptySet(),
            connectionLostBannerVisible = false,
        )
    }

    fun createUiData(data: List<HomeItem>, account: Account?): HomeState.UiData {
        val filters = data.find { it is HomeItem.Filters } as HomeItem.Filters
        val stores = data.find { it is HomeItem.Collection } as HomeItem.Collection
        return HomeState.UiData(
            account = account?.toAccountInfo(),
//            banner = Banner(
//                image = data.banner.image,
//                title = data.banner.title,
//                description = data.banner.description,
//            ),
            filters = filters.filters.map {
                ChipItem(
                    id = it.id.toInt(),
                    name = it.name
                )
            },
//            collections = data.collections.map { apiModel ->
//                Collection(apiModel.name, apiModel.products.map(::mapProduct))
//            },
            storeUI = stores.stores.map(::mapStore),
        )
    }

    fun updateProfile(uiData: HomeState.UiData, account: Account?): HomeState.UiData {
        return uiData.copy(
            account = account?.toAccountInfo()
        )
    }

    private fun Account.toAccountInfo(): AccountInfo {
        return AccountInfo(
            fullName = name,
            image = image,
        )
    }

    private fun mapProduct(apiModel: ProductApi): Product {
        return Product(
            name = apiModel.name,
            price = Price(apiModel.price.value.toInt(), apiModel.price.currency),
            id = apiModel.id,
            image =  apiModel.image,
        )
    }

    private fun mapStore(apiModel: Store): StoreUI {
        return StoreUI(
            id = apiModel.id,
            name = apiModel.name,
            image = apiModel.image,
            rating = Rating(
                average = 0f,
                count = 0,
            ),
            products = emptyList(),
        )
    }
}