package kz.nfactorial.nfactorialapp.home.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeComponentApi(
    val filters: List<FilterApi>,
    val banner: BannerApi,
    val collections: List<CollectionApi>,
    val storeCollections: List<StoreCollectionApi>,
)

@Serializable
data class PriceApi(
    val value: Double,
    val currency: String
)

@Serializable
data class ProductApi(
    val id: String,
    val name: String,
    val price: PriceApi,
    val image: String
)

@Serializable
data class RatingApi(
    val average: Double,
    val count: Int
)

@Serializable
data class StoreApi(
    val id: String,
    val name: String,
    val image: String,
    val rating: RatingApi,
    val products: List<ProductApi> = emptyList(),
)

@Serializable
data class StoreLight(
    val id: String,
    val name: String,
    val rating: RatingApi
)

@Serializable
data class ProfileApi(
    var name: String,
    var size: String
)

@Serializable
data class CollectionApi(
    val id: String,
    val name: String,
    val products: List<ProductApi>
)

@Serializable
data class StoreCollectionApi(
    val id: String,
    val name: String,
    val stores: List<StoreApi>
)

@Serializable
data class BannerApi(
    val id: String,
    val image: String,
    val title: String,
    val description: String,
)

@Serializable
data class FilterApi(
    val name: String,
    val id: String,
)