package kz.nfactorial.nfactorialapp.photo.di

import kz.nfactorial.nfactorialapp.photo.presentation.ChoosePhotoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val choosePhotoModule = module {

    viewModel {
        ChoosePhotoViewModel(
            profileRepository = get(),
        )
    }
}