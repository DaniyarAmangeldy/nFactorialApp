package kz.nfactorial.nfactorialapp.registration.di

import kz.nfactorial.nfactorialapp.registration.data.api.ProfileApiService
import kz.nfactorial.nfactorialapp.registration.data.repository.ProfileRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val profileModule = module {

    factory {
        ProfileRepository(
            profileApiService = get(),
            accountProvider = get(),
        )
    }

    single {
        get<Retrofit>().create<ProfileApiService>()
    }
}