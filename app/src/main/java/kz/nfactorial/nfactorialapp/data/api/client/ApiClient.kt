package kz.nfactorial.nfactorialapp.data.api.client

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import java.util.concurrent.TimeUnit
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class ApiClient(context: Context) {

    val client by lazy {
        OkHttpClient
            .Builder()
            .readTimeout(60_000, TimeUnit.MILLISECONDS)
            .writeTimeout(60_000, TimeUnit.MILLISECONDS)
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }

    val retrofit by lazy {
        val json = Json {
            encodeDefaults = true
        }
        Retrofit.Builder()
            .baseUrl("https://nfactorialappbackend.onrender.com/")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .build()
    }
}