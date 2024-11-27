package kz.nfactorial.nfactorialapp.home.data.account

import kz.nfactorial.nfactorialapp.home.data.model.Account

interface AccountProvider {

    fun getAccount(): Account?

    fun setName(name: String)

    fun setSize(size: Int?)
}