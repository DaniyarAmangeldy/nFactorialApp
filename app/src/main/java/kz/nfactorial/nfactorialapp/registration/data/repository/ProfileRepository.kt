package kz.nfactorial.nfactorialapp.registration.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kz.nfactorial.nfactorialapp.home.data.account.AccountProvider
import kz.nfactorial.nfactorialapp.home.data.model.ProfileApi
import kz.nfactorial.nfactorialapp.registration.data.api.ProfileApiService
import kz.nfactorial.nfactorialapp.registration.data.model.ProfileRequestApi
import kz.nfactorial.nfactorialapp.registration.presentation.model.Account

class ProfileRepository(
    private val profileApiService: ProfileApiService,
    private val accountProvider: AccountProvider,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getProfile(): Flow<Account> {
        return accountProvider.getAccount()
            .take(1)
            .map { account -> account?.let(::mapToAccount) }
            .flatMapLatest { account ->
                flow {
                    account?.let { emit(it) }
                    val response = profileApiService.getProfile().let(::mapToAccount)
                    accountProvider.setSize(response.size)
                    accountProvider.setName(response.name)
                    emit(response)
                }
            }
    }

    suspend fun setSize(size: Int): Flow<Account> {
        return flow {
            val response = profileApiService
                .updateProfile(ProfileRequestApi(size = size.toString()))
                .let(::mapToAccount)
            accountProvider.setSize(response.size)
            emit(response)
        }
    }

    suspend fun setName(name: String): Flow<Account> {
        return flow {
            val response = profileApiService
                .updateProfile(ProfileRequestApi(name = name))
                .let(::mapToAccount)
            accountProvider.setName(response.name)
            emit(response)
        }
    }

    private fun mapToAccount(apiModel: ProfileApi): Account {
        return Account(
            name = apiModel.name,
            size = apiModel.size.toIntOrNull() ?: -1,
        )
    }

    private fun mapToAccount(dbModel: kz.nfactorial.nfactorialapp.home.data.model.Account): Account {
        return Account(
            name = dbModel.name,
            size = dbModel.size ?: -1,
        )
    }
}