package com.example.productapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products WHERE quantity > 0")
    fun getCartItems(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products")
    suspend fun getAllProductsOnce(): List<ProductEntity> // Получить товары без Flow (для проверки при запуске)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query("UPDATE products SET quantity = quantity + 1 WHERE id = :productId")
    suspend fun incrementQuantity(productId: Int)

    @Query("UPDATE products SET quantity = quantity - 1 WHERE id = :productId AND quantity > 1")
    suspend fun decrementQuantity(productId: Int)
}