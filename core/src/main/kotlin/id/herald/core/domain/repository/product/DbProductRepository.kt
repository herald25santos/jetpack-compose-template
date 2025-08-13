package id.herald.core.domain.repository.product

import id.herald.core.data.datasource.local.db.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

/** Created by Herald Santos on 04/12/2024. */

interface DbProductRepository {
    fun getProductsDb(): Flow<MutableList<ProductEntity>>
    fun getProductByIdDb(id: Long): Flow<ProductEntity>
    suspend fun insertProductDb(product: ProductEntity): Long
    suspend fun deleteProductDb(product: ProductEntity): Int
}