package kz.nfactorial.nfactorialapp.home.data.account

import kz.nfactorial.nfactorialapp.home.data.model.Account

interface AccountProvider {

    suspend fun getAccount(): Account?

    suspend fun setName(name: String)

    suspend fun setSize(size: Int?)
}