package kz.nfactorial.nfactorialapp.registration.di

import kz.nfactorial.nfactorialapp.registration.data.api.ProfileApiService
import kz.nfactorial.nfactorialapp.registration.data.repository.ProfileRepositoryImpl
import kz.nfactorial.nfactorialapp.registration.domain.ProfileRepository
import kz.nfactorial.nfactorialapp.registration.presentation.RegistrationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val profileModule = module {


    viewModel {
        RegistrationViewModel(
            profileRepository = get(),
        )
    }

    factory<ProfileRepository> {
        ProfileRepositoryImpl(
            profileApiService = get(),
            accountProvider = get(),
        )
    }

    single {
        get<Retrofit>().create<ProfileApiService>()
    }
}