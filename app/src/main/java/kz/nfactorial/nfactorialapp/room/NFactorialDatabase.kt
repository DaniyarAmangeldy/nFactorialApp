package kz.nfactorial.nfactorialapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kz.nfactorial.nfactorialapp.home.data.db.dao.AccountDao
import kz.nfactorial.nfactorialapp.home.data.db.entity.AccountDb

@Database(entities = [AccountDb::class], version = 1)
abstract class NFactorialDatabase: RoomDatabase() {

    abstract fun getAccountDao(): AccountDao
}

object DatabaseHolder {

    private var _database: NFactorialDatabase? = null

    val database: NFactorialDatabase get() = _database!!

    fun getOrCreate(context: Context): NFactorialDatabase {
        if (_database == null) {
            _database = Room.databaseBuilder(
                context,
                NFactorialDatabase::class.java,
                "nfactorial-database",
            )
                .allowMainThreadQueries()
                .build()
        }

        return database
    }
}