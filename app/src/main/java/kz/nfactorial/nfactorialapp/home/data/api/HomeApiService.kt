package kz.nfactorial.nfactorialapp.home.data.api

import kz.nfactorial.nfactorialapp.home.data.model.HomeComponentApi
import retrofit2.http.GET

interface HomeApiService {

    @GET("home")
    suspend fun getHome(): Result<HomeComponentApi>
}