package id.herald.core.util

import id.herald.core.data.model.Product
import id.herald.core.data.model.ProductResponse

/** Created by Herald Santos on 04/12/2024. */

object UtilTests {
    val dummyProduct = Product("Product", "Product 1")
    val dummyProductResponse = ProductResponse(0, mutableListOf(dummyProduct))
}