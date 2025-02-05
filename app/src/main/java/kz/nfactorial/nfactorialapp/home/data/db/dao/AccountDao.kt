package kz.nfactorial.nfactorialapp.home.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import kz.nfactorial.nfactorialapp.home.data.db.entity.AccountDb

@Dao
interface AccountDao {

    @Query("SELECT * FROM AccountDb")
    fun getAccountFlow(): Flow<AccountDb?>

    @Query("SELECT * FROM AccountDb LIMIT 1")
    fun getAccount(): AccountDb?

    @Transaction
    suspend fun deleteAllAndInsert(accountDb: AccountDb) {
        deleteAll()
        insertAccount(accountDb)
    }

    @Insert
    suspend fun insertAccount(accountDb: AccountDb)

    @Query("DELETE from AccountDb")
    suspend fun deleteAll()
}