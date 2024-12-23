package kz.nfactorial.nfactorialapp.data.api.client

import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient

class ApiClient {

    val client by lazy {
        OkHttpClient
            .Builder()
            .readTimeout(60_000, TimeUnit.MILLISECONDS)
            .writeTimeout(60_000, TimeUnit.MILLISECONDS)
            .build()
    }
}