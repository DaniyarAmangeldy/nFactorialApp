package kz.nfactorial.nfactorialapp.registration.data.repository

import kz.nfactorial.nfactorialapp.home.data.account.AccountProvider
import kz.nfactorial.nfactorialapp.home.data.model.ProfileApi
import kz.nfactorial.nfactorialapp.registration.data.api.ProfileApiService
import kz.nfactorial.nfactorialapp.registration.data.model.ProfileRequestApi
import kz.nfactorial.nfactorialapp.registration.presentation.model.Account

class ProfileRepository(
    private val profileApiService: ProfileApiService,
    private val accountProvider: AccountProvider,
) {

    suspend fun getProfile(): Result<Account> {
        return profileApiService.getProfile()
            .map(::mapToAccount)
            .onSuccess {
                accountProvider.setSize(it.size)
                accountProvider.setName(it.name)
            }
    }

    suspend fun setSize(size: Int): Result<Account> {
        return profileApiService.updateProfile(ProfileRequestApi(size = size.toString()))
            .map(::mapToAccount)
            .onSuccess { accountProvider.setSize(it.size) }
    }

    suspend fun setName(name: String): Result<Account> {
        return profileApiService.updateProfile(ProfileRequestApi(name = name))
            .map(::mapToAccount)
            .onSuccess { accountProvider.setName(name) }
    }

    private fun mapToAccount(apiModel: ProfileApi): Account {
        return Account(
            name = apiModel.name,
            size = apiModel.size.toIntOrNull() ?: -1,
        )
    }
}