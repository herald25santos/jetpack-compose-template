package id.herald.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.herald.core.data.datasource.local.db.AppDatabase
import id.herald.core.util.UtilConstants.DB_JET_SHOPEE
import javax.inject.Singleton

/** Created by Herald Santos on 04/12/2024. */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_JET_SHOPEE).build()
    }
}