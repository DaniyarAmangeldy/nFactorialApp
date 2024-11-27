package kz.nfactorial.nfactorialapp.home.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountDb(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("size")
    val size: Int?,
)