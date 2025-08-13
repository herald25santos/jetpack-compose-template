package id.herald.core.data.model.mapper

import id.herald.core.data.datasource.local.db.entity.ProductEntity
import id.herald.core.data.model.Product

/** Created by Herald Santos on 04/12/2024. */

object ProductMapper {
    fun mapFromProductToEntity(product: Product) =
        ProductEntity(product.id, product.description, product.price, product.thumbnail, product.title)
}