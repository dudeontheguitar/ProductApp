package com.example.productapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
}
