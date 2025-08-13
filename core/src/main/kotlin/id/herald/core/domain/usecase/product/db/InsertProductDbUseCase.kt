package id.herald.core.domain.usecase.product.db

import id.herald.core.data.datasource.local.db.entity.ProductEntity
import id.herald.core.domain.repository.product.DbProductRepository
import id.herald.core.domain.usecase.BaseUseCaseSuspend
import javax.inject.Inject

/** Created by Herald Santos on 04/12/2024. */

class InsertProductDbUseCase @Inject constructor(
    private val dbProductRepository: DbProductRepository
) : BaseUseCaseSuspend<ProductEntity, Long>() {
    override suspend fun execute(params: ProductEntity): Long {
        return dbProductRepository.insertProductDb(params)
    }
}