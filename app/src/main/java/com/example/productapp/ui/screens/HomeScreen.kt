package com.example.productapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.productapp.data.local.ProductEntity
import com.example.productapp.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val products by viewModel.products.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Home") },
                actions = {
                    IconButton(onClick = {navController.navigate("cart")}){
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                    }
                }
            )
        }
    ){ paddingValues ->
       LazyVerticalGrid(
           columns = GridCells.Fixed(2),
           contentPadding = PaddingValues(16.dp),
           modifier = Modifier.padding(paddingValues)
       ) {
           items(products){ product ->
               ProductItem(product, viewModel)
           }
       }
    }
}

@Composable
fun ProductItem(product: ProductEntity, viewModel: HomeViewModel) {
    Column(modifier = Modifier.padding(8.dp)) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = "Product Image",
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = product.name,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            "Price: ${product.price}",
            style = MaterialTheme.typography.bodyMedium
        )
        if(product.quantity > 0){
            Row {
                Button(onClick = {viewModel.decrementQuantity(product.id)}) { Text("-")}
                Text("${product.quantity}")
                Button(onClick = {viewModel.incrementQuantity(product.id)}) { Text("+")}
            }
        }else{
            Button(onClick = {viewModel.incrementQuantity(product.id)}) { Text("Add to Cart")}
        }

    }
}
