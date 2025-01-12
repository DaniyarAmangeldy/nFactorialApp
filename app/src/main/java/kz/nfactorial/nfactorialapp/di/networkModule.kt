package kz.nfactorial.nfactorialapp.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import java.util.concurrent.TimeUnit
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single {
        OkHttpClient
            .Builder()
            .readTimeout(60_000, TimeUnit.MILLISECONDS)
            .writeTimeout(60_000, TimeUnit.MILLISECONDS)
            .addInterceptor(ChuckerInterceptor(androidContext()))
            .build()
    }

    single {
        Json {
            encodeDefaults = true
        }
    }

    single {
        val json: Json = get()
        val okHttpClient: OkHttpClient = get()
        Retrofit.Builder()
            .baseUrl("https://nfactorialappbackend.onrender.com/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .build()
    }
}