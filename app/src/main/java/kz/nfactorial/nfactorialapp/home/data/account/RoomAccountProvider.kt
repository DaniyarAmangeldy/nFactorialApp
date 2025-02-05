package kz.nfactorial.nfactorialapp.home.data.account

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import kz.nfactorial.nfactorialapp.home.data.db.dao.AccountDao
import kz.nfactorial.nfactorialapp.home.data.db.entity.AccountDb
import kz.nfactorial.nfactorialapp.home.data.model.Account

class RoomAccountProvider(
    private val accountDao: AccountDao,
) : AccountProvider {

    override fun getAccount(): Flow<Account?> {
        return accountDao.getAccountFlow()
            .map { it?.toAccount() }
            .onEmpty { emit(null) }
    }

    override suspend fun setName(name: String) {
        val accountDb = accountDao.getAccount()?.copy(name = name) ?: AccountDb(0, name, null, null)
        accountDao.deleteAllAndInsert(accountDb)
    }

    override suspend fun setImage(image: Uri?) {
        val accountDb = accountDao.getAccount()?.copy(image = image?.toString()) ?: AccountDb(0, "", null, image?.toString())
        accountDao.deleteAllAndInsert(accountDb)
    }

    override suspend fun setSize(size: Int?) {
        val accountDb = accountDao.getAccount()?.copy(size = size) ?: AccountDb(0, "", size, null)
        accountDao.deleteAllAndInsert(accountDb)
    }

    private fun AccountDb.toAccount(): Account {
        return Account(name = name, size = size, image = image?.let(Uri::parse))
    }
}