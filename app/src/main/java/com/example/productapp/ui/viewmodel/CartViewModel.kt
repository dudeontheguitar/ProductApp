package com.example.productapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productapp.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    val cartItems = repository.cartItems

    fun incrementQuantity(productId: Int) {
        viewModelScope.launch {
            repository.incrementQuantity(productId)
        }
    }

    fun decrementQuantity(productId: Int) {
        viewModelScope.launch {
            repository.decrementQuantity(productId)
        }
    }

    fun updateQuantity(productId: Int, newQuantity: Int){
        viewModelScope.launch {
            repository.updateProductQuantity(productId, newQuantity)
        }
    }
}