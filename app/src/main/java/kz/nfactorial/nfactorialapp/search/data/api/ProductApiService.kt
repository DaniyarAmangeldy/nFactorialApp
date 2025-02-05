package kz.nfactorial.nfactorialapp.search.data.api

import kz.nfactorial.nfactorialapp.search.data.model.ProductsApi
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiService {

    @GET("products")
    suspend fun searchProducts(
        @Query("query") query: String,
    ): ProductsApi

    @GET("products")
    suspend fun getProducts(): ProductsApi
}