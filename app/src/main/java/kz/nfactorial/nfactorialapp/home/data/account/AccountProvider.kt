package kz.nfactorial.nfactorialapp.home.data.account

import kotlinx.coroutines.flow.Flow
import kz.nfactorial.nfactorialapp.home.data.model.Account

interface AccountProvider {

    fun getAccount(): Flow<Account?>

    suspend fun setName(name: String)

    suspend fun setSize(size: Int?)
}