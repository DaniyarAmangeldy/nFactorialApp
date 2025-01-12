package kz.nfactorial.nfactorialapp.search.di

import kz.nfactorial.nfactorialapp.search.data.api.ProductApiService
import kz.nfactorial.nfactorialapp.search.data.repository.ProductRepository
import kz.nfactorial.nfactorialapp.search.presentation.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val searchModule = module {

    viewModel {
        SearchViewModel(
            productRepository = get(),
        )
    }

    factory {
        ProductRepository(
            productApiService = get(),
        )
    }

    single {
        get<Retrofit>().create<ProductApiService>()
    }
}