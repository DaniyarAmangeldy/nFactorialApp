package kz.nfactorial.nfactorialapp.home.data.account

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kz.nfactorial.nfactorialapp.home.data.db.entity.AccountDb
import kz.nfactorial.nfactorialapp.home.data.model.Account
import kz.nfactorial.nfactorialapp.room.DatabaseHolder

class RoomAccountProvider(
    context: Context,
) : AccountProvider {

    private val dao = DatabaseHolder.getOrCreate(context.applicationContext).getAccountDao()

    override fun getAccount(): Flow<Account?> {
        return dao.getAccountFlow().map { it?.toAccount() }
    }

    override suspend fun setName(name: String) {
        val accountDb = dao.getAccount()?.copy(name = name) ?: AccountDb(0, name, null)
        dao.deleteAllAndInsert(accountDb)
    }

    override suspend fun setSize(size: Int?) {
        val accountDb = dao.getAccount()?.copy(size = size) ?: AccountDb(0, "", size)
        dao.deleteAllAndInsert(accountDb)
    }

    private fun AccountDb.toAccount(): Account {
        return Account(name = name, size = size)
    }
}