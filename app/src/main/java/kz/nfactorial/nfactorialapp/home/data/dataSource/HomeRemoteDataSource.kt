package kz.nfactorial.nfactorialapp.home.data.dataSource

import kz.nfactorial.nfactorialapp.home.data.api.HomeApiService
import kz.nfactorial.nfactorialapp.home.data.model.HomeComponentApi

// High Level Module
// Interface Adapters
interface HomeRemoteDataSource {

    suspend fun getHomeComponents(): HomeComponentApi
}

// Low Level Module
// Frameworks
class HomeRemoteDataSourceImpl(
    private val homeApiService: HomeApiService,
): HomeRemoteDataSource {

    override suspend fun getHomeComponents(): HomeComponentApi {
        return homeApiService.getHome()
    }
}