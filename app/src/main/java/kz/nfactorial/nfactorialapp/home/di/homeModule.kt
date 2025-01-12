package kz.nfactorial.nfactorialapp.home.di

import kz.nfactorial.nfactorialapp.home.data.api.HomeApiService
import kz.nfactorial.nfactorialapp.home.data.repository.HomeRepository
import kz.nfactorial.nfactorialapp.home.presentation.HomeViewModel
import kz.nfactorial.nfactorialapp.home.presentation.factory.HomeStateFactory
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val homeModule = module {

    viewModel {
        HomeViewModel(
            homeRepository = get(),
            profileRepository = get(),
            homeStateFactory = get(),
        )
    }

    factory {
        HomeRepository(
            accountProvider = get(),
            homeApiService = get(),
        )
    }

    factory {
        HomeStateFactory()
    }

    factory {
        get<Retrofit>().create<HomeApiService>()
    }

}