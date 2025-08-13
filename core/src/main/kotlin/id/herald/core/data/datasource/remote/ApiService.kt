package id.herald.core.data.datasource.remote

import id.herald.core.data.model.Product
import id.herald.core.data.model.ProductResponse
import retrofit2.http.*

/** Created by Herald Santos on 04/12/2024. */

interface ApiService {

    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): Product

    @GET("products/search")
    suspend fun searchProduct(
        @Query("q") query: String
    ): ProductResponse
}