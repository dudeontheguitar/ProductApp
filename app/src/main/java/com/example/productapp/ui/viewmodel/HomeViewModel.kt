package com.example.productapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productapp.data.local.ProductEntity
import com.example.productapp.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel(){
    val products = repository.allProducts

    fun incrementQuantity(productId: Int){
        viewModelScope.launch {
            repository.incrementQuantity(productId)
        }
    }

    fun decrementQuantity(productId: Int){
        viewModelScope.launch {
            repository.decrementQuantity(productId)
        }
    }

    fun delete(product: ProductEntity){
        viewModelScope.launch {
            repository.deleteProduct(product)
        }
    }

    fun updateQuantity(productId: Int, newQuantity: Int){
        viewModelScope.launch {
            repository.updateProductQuantity(productId, newQuantity)
        }
    }

    fun clear(){
        viewModelScope.launch {
            repository.clearDatabse()
        }
    }
}
