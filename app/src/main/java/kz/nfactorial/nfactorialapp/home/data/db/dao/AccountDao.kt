package kz.nfactorial.nfactorialapp.home.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kz.nfactorial.nfactorialapp.home.data.db.entity.AccountDb

@Dao
interface AccountDao {

    @Query("SELECT * FROM AccountDb LIMIT 1")
    suspend fun getAccount(): AccountDb?

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