package kz.nfactorial.nfactorialapp

import android.app.Application
import kz.nfactorial.nfactorialapp.di.dbModule
import kz.nfactorial.nfactorialapp.di.networkModule
import kz.nfactorial.nfactorialapp.home.di.homeModule
import kz.nfactorial.nfactorialapp.photo.di.choosePhotoModule
import kz.nfactorial.nfactorialapp.registration.di.profileModule
import kz.nfactorial.nfactorialapp.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NFactorialAppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NFactorialAppApplication)
            modules(
                networkModule,
                dbModule,
                profileModule,
                choosePhotoModule,
                searchModule,
                homeModule,
            )
        }
    }
}