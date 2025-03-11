package com.example.productapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.productapp.data.local.ProductEntity
import com.example.productapp.ui.viewmodel.CartViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, viewModel: CartViewModel = hiltViewModel()) {
    val cartItems by viewModel.cartItems.collectAsState(initial = emptyList())
    val totalPrice = cartItems.sumOf { it.price * it.quantity }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartItems) { product ->
                    CartItem(product, viewModel)
                }
            }
            Text("Общая сумма: $totalPrice₽", style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
fun CartItem(product: ProductEntity, viewModel: CartViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(product.name)
        if(product.quantity == 1){
            Row {
                Button(onClick = {viewModel.updateQuantity(product.id, 0)}) { Text("del",  fontSize = 10.sp) }
                Text("${product.quantity}")
                Button(onClick = {viewModel.incrementQuantity(product.id)}) { Text("+",  fontSize = 10.sp)}
            }
        }else{
            Row {
                Button(onClick = { viewModel.decrementQuantity(product.id) }) { Text("-",  fontSize = 10.sp) }
                Text("${product.quantity}")
                Button(onClick = { viewModel.incrementQuantity(product.id) }) { Text("+",  fontSize = 10.sp) }
            }
        }
    }
}
