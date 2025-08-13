package id.herald.core.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import id.herald.core.data.datasource.local.db.dao.ProductDao
import id.herald.core.data.datasource.local.db.entity.ProductEntity

/** Created by Herald Santos on 04/12/2024. */

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}