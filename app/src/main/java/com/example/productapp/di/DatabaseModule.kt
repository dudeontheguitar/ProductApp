package com.example.productapp.di

import android.content.Context
import androidx.room.Room
import com.example.productapp.data.local.AppDatabase
import com.example.productapp.data.local.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) // Доступно во всём приложении
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "product_database"
        ).build()
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao{
        return database.productDao()
    }
}