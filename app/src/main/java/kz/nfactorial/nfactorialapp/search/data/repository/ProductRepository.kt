package kz.nfactorial.nfactorialapp.search.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kz.nfactorial.nfactorialapp.home.data.model.ProductApi
import kz.nfactorial.nfactorialapp.home.presentation.models.Price
import kz.nfactorial.nfactorialapp.home.presentation.models.Product
import kz.nfactorial.nfactorialapp.search.data.api.ProductApiService

class ProductRepository(
    private val productApiService: ProductApiService,
) {

    fun searchProductByQuery(query: String): Flow<List<Product>> {
        return flow { emit(productApiService.searchProducts(query).products) }
            .map { products -> products.map(::mapProduct) }
            .flowOn(Dispatchers.IO)
    }

    fun getProducts(): Flow<List<Product>> {
        return flow { emit(productApiService.getProducts().products) }
            .map { products -> products.map(::mapProduct) }
            .flowOn(Dispatchers.IO)
    }

    private fun mapProduct(apiModel: ProductApi): Product {
        return Product(
            name = apiModel.name,
            price = Price(apiModel.price.value.toInt(), apiModel.price.currency),
            id = apiModel.id,
            image =  apiModel.image,
        )
    }
}