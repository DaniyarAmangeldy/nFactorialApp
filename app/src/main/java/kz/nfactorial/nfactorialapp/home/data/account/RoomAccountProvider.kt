package kz.nfactorial.nfactorialapp.home.data.account

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import kz.nfactorial.nfactorialapp.home.data.db.entity.AccountDb
import kz.nfactorial.nfactorialapp.home.data.model.Account
import kz.nfactorial.nfactorialapp.room.DatabaseHolder

class RoomAccountProvider(
    context: Context,
) : AccountProvider {

    private val dao = DatabaseHolder.getOrCreate(context.applicationContext).getAccountDao()

    override fun getAccount(): Flow<Account?> {
        return dao.getAccountFlow()
            .map { it?.toAccount() }
            .onEmpty { emit(null) }
    }

    override suspend fun setName(name: String) {
        val accountDb = dao.getAccount()?.copy(name = name) ?: AccountDb(0, name, null, null)
        dao.deleteAllAndInsert(accountDb)
    }

    override suspend fun setImage(image: Uri?) {
        val accountDb = dao.getAccount()?.copy(image = image?.toString()) ?: AccountDb(0, "", null, image?.toString())
        dao.deleteAllAndInsert(accountDb)
    }

    override suspend fun setSize(size: Int?) {
        val accountDb = dao.getAccount()?.copy(size = size) ?: AccountDb(0, "", size, null)
        dao.deleteAllAndInsert(accountDb)
    }

    private fun AccountDb.toAccount(): Account {
        return Account(name = name, size = size, image = image?.let(Uri::parse))
    }
}