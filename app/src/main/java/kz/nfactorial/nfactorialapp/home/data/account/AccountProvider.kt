package kz.nfactorial.nfactorialapp.home.data.account

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import kz.nfactorial.nfactorialapp.home.data.model.Account

interface AccountProvider {

    fun getAccount(): Flow<Account?>

    suspend fun setName(name: String)

    suspend fun setSize(size: Int?)

    suspend fun setImage(image: Uri?)
}