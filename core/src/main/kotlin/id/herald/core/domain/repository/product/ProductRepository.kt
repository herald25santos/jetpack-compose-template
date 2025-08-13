package id.herald.core.domain.repository.product

import id.herald.core.data.model.Product
import id.herald.core.data.model.ProductResponse
import kotlinx.coroutines.flow.Flow

/** Created by Herald Santos on 04/12/2024. */

interface ProductRepository {
    fun getProductsApiCall(): Flow<ProductResponse> // this is sample not using `suspend`
    fun getProductByIdApiCall(id: Int): Flow<Product>
    suspend fun searchProductApiCall(query: String): Flow<ProductResponse> // this is sample using `suspend`
}