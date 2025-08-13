package id.herald.core.domain.usecase.product

import id.herald.core.data.model.ProductResponse
import id.herald.core.domain.repository.product.ProductRepository
import id.herald.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : BaseUseCase<Unit, Flow<ProductResponse>>() {
    override fun execute(params: Unit): Flow<ProductResponse> {
        return productRepository.getProductsApiCall()
    }
}