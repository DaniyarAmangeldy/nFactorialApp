package kz.nfactorial.nfactorialapp.search.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kz.nfactorial.nfactorialapp.home.data.model.ProductApi

@Serializable
data class ProductsApi(

    @SerialName("products")
    val products: List<ProductApi>
)