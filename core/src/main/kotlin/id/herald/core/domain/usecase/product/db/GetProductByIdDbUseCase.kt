package id.herald.core.domain.usecase.product.db

import id.herald.core.data.datasource.local.db.entity.ProductEntity
import id.herald.core.domain.repository.product.DbProductRepository
import id.herald.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/** Created by Herald Santos on 04/12/2024. */

class GetProductByIdDbUseCase @Inject constructor(
    private val dbProductRepository: DbProductRepository
) : BaseUseCase<Long, Flow<ProductEntity>>() {
    override fun execute(params: Long): Flow<ProductEntity> {
        return dbProductRepository.getProductByIdDb(params)
    }
}