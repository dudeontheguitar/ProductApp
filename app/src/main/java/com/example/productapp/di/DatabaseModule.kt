package com.example.productapp.di

import android.content.Context
import androidx.room.Room
import com.example.productapp.data.local.AppDatabase
import com.example.productapp.data.local.PrepopulateDatabase
import com.example.productapp.data.local.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        val db = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "product_database"
        ).build()

        PrepopulateDatabase.insertDefaultProducts(db.productDao())

        return db
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }
}
