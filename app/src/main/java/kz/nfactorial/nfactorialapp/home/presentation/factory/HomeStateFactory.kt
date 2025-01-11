package kz.nfactorial.nfactorialapp.home.presentation.factory

import kz.nfactorial.nfactorialapp.R
import kz.nfactorial.nfactorialapp.home.data.model.HomeComponentApi
import kz.nfactorial.nfactorialapp.home.data.model.ProductApi
import kz.nfactorial.nfactorialapp.home.data.model.StoreApi
import kz.nfactorial.nfactorialapp.home.presentation.HomeState
import kz.nfactorial.nfactorialapp.home.presentation.models.AccountInfo
import kz.nfactorial.nfactorialapp.home.presentation.models.Banner
import kz.nfactorial.nfactorialapp.home.presentation.models.ChipItem
import kz.nfactorial.nfactorialapp.home.presentation.models.Collection
import kz.nfactorial.nfactorialapp.home.presentation.models.Price
import kz.nfactorial.nfactorialapp.home.presentation.models.Product
import kz.nfactorial.nfactorialapp.home.presentation.models.Rating
import kz.nfactorial.nfactorialapp.home.presentation.models.Store
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

    fun createUiData(data: HomeComponentApi, account: Account?): HomeState.UiData {
        return HomeState.UiData(
            account = account?.toAccountInfo(),
            banner = Banner(
                image = data.banner.image,
                title = data.banner.title,
                description = data.banner.description,
            ),
            filters = data.filters.map {
                ChipItem(
                    id = it.id.toInt(),
                    name = it.name
                )
            },
            collections = data.collections.map { apiModel ->
                Collection(apiModel.name, apiModel.products.map(::mapProduct))
            },
            stores = data.storeCollections.flatMap { it.stores }.map(::mapStore),
        )
    }

    private fun Account.toAccountInfo(): AccountInfo {
        return AccountInfo(
            fullName = name,
            image = R.drawable.ic_spiderman,
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

    private fun mapStore(apiModel: StoreApi): Store {
        return Store(
            id = apiModel.id,
            name = apiModel.name,
            image = apiModel.image,
            rating = Rating(
                average = apiModel.rating.average.toFloat(),
                count = apiModel.rating.count,
            ),
            products = apiModel.products.map(::mapProduct)
        )
    }
}