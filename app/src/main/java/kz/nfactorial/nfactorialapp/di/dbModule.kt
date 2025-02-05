package kz.nfactorial.nfactorialapp.di

import androidx.room.Room
import kz.nfactorial.nfactorialapp.home.data.account.AccountProvider
import kz.nfactorial.nfactorialapp.home.data.account.RoomAccountProvider
import kz.nfactorial.nfactorialapp.room.NFactorialDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {

    single<AccountProvider> {
        RoomAccountProvider(accountDao = get())
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            NFactorialDatabase::class.java,
            "nfactorial-database",
        ).build()
    }

    single {
        val database: NFactorialDatabase = get()
        database.getAccountDao()
    }
}