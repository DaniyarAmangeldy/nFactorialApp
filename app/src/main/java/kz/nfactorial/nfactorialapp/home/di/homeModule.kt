package kz.nfactorial.nfactorialapp.home.di

import kz.nfactorial.nfactorialapp.home.data.api.HomeApiService
import kz.nfactorial.nfactorialapp.home.data.dataSource.HomeRemoteDataSource
import kz.nfactorial.nfactorialapp.home.data.dataSource.HomeRemoteDataSourceImpl
import kz.nfactorial.nfactorialapp.home.data.mappers.HomeComponentApiToDomainMapper
import kz.nfactorial.nfactorialapp.home.data.repository.HomeRepositoryImpl
import kz.nfactorial.nfactorialapp.home.domain.repository.entity.HomeRepository
import kz.nfactorial.nfactorialapp.home.domain.usecases.GetHomeComponentsUseCase
import kz.nfactorial.nfactorialapp.home.presentation.HomeViewModel
import kz.nfactorial.nfactorialapp.home.presentation.factory.HomeStateFactory
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val homeModule = module {

    viewModel {
        HomeViewModel(
            profileRepository = get(),
            homeStateFactory = get(),
            getHomeComponentsUseCase = get(),
        )
    }

    factory {
        GetHomeComponentsUseCase(
            homeRepository = get(),
        )
    }

    factory<HomeRepository> {
        HomeRepositoryImpl(
            accountProvider = get(),
            homeRemoteDataSource = get(),
            homeComponentApiToDomainMapper = HomeComponentApiToDomainMapper(),
        )
    }

    factory<HomeRemoteDataSource> {
        HomeRemoteDataSourceImpl(
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