package id.herald.core.data.datasource.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

/** Created by Herald Santos on 04/12/2024. */

@Entity(tableName = "table_shop", primaryKeys = ["id"])
data class ProductEntity(
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "price")
    val price: Double? = null,

    @ColumnInfo(name = "thumbnail")
    val thumbnail: String? = null,

    @ColumnInfo(name = "title")
    val title: String? = null
)
