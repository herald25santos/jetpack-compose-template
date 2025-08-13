package id.herald.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.herald.core.data.datasource.local.db.AppDatabase
import id.herald.core.data.datasource.remote.ApiService
import id.herald.core.data.repository.product.DbProductRepositoryImpl
import id.herald.core.data.repository.product.ProductRepositoryImpl
import id.herald.core.domain.repository.product.DbProductRepository
import id.herald.core.domain.repository.product.ProductRepository
import javax.inject.Singleton

/** Created by Herald Santos on 04/12/2024. */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService): ProductRepository {
        return ProductRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideDbProductRepository(db: AppDatabase): DbProductRepository {
        return DbProductRepositoryImpl(db)
    }
}