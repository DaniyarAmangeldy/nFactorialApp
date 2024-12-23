package kz.nfactorial.nfactorialapp.home.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kz.nfactorial.nfactorialapp.data.api.client.ApiClient
import kz.nfactorial.nfactorialapp.home.data.account.AccountProvider
import kz.nfactorial.nfactorialapp.home.data.api.HomeApiService
import kz.nfactorial.nfactorialapp.home.data.model.Account
import kz.nfactorial.nfactorialapp.home.data.model.HomeComponentApi
import okhttp3.Request
import retrofit2.create

class HomeRepository(
    private val apiClient: ApiClient,
    private val accountProvider: AccountProvider,
) {


    suspend fun getAccountInfo(): Account? {
        return accountProvider.getAccount()
    }

    suspend fun getHomeComponents(): Result<HomeComponentApi> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val request = Request
                    .Builder()
                    .get()
                    .url("https://nfactorialappbackend.onrender.com/home")
                    .build()
                val responseJson = apiClient.client.newCall(request).execute().body?.string()
                if (!responseJson.isNullOrEmpty()) {
                    Json.decodeFromString<HomeComponentApi>(responseJson)
                } else {
                    error("responseJson is null")
                }
            }
        }
    }
}