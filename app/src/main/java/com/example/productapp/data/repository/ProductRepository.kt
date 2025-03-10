package com.example.productapp.data.repository

import com.example.productapp.data.local.ProductDao
import com.example.productapp.data.local.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {
    val allProducts: Flow<List<ProductEntity>> = productDao.getAllProducts()
    val cartItems: Flow<List<ProductEntity>> = productDao.getCartItems()

    suspend fun addProduct(product: ProductEntity){
        productDao.insertProduct(product)
    }
    suspend fun updateProduct(product: ProductEntity){
        productDao.updateProduct(product)
    }
    suspend fun deleteProduct(product: ProductEntity){
        productDao.deleteProduct(product)
    }
    suspend fun incrementQuantity(productId: Int) {
        productDao.incrementQuantity(productId)
    }

    suspend fun decrementQuantity(productId: Int) {
        productDao.decrementQuantity(productId)
    }
}