package kz.nfactorial.nfactorialapp.home.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    fun getAccountInfo(): Flow<Account?> {
        return accountProvider.getAccount()
    }

    suspend fun getHomeComponents(): Flow<HomeComponentApi> {
        return flow { emit(homeApiService.getHome()) }
    }
}