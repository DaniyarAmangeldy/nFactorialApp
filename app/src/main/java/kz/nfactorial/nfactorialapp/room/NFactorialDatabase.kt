package kz.nfactorial.nfactorialapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.nfactorial.nfactorialapp.home.data.db.dao.AccountDao
import kz.nfactorial.nfactorialapp.home.data.db.entity.AccountDb

@Database(entities = [AccountDb::class], version = 1)
abstract class NFactorialDatabase: RoomDatabase() {

    abstract fun getAccountDao(): AccountDao
}