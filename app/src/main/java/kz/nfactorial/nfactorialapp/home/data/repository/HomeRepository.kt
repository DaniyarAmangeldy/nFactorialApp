package kz.nfactorial.nfactorialapp.home.data.repository

import kz.nfactorial.nfactorialapp.data.api.client.ApiClient
import kz.nfactorial.nfactorialapp.home.data.account.AccountProvider
import kz.nfactorial.nfactorialapp.home.data.api.HomeApiService
import kz.nfactorial.nfactorialapp.home.data.model.Account
import kz.nfactorial.nfactorialapp.home.data.model.HomeComponentApi
import retrofit2.create

class HomeRepository(
    private val apiClient: ApiClient,
    private val accountProvider: AccountProvider,
    private val homeApiService: HomeApiService = apiClient.retrofit.create(),
) {

    suspend fun getAccountInfo(): Account? {
        return accountProvider.getAccount()
    }

    suspend fun getHomeComponents(): Result<HomeComponentApi> {
        return homeApiService.getHome()
    }
}