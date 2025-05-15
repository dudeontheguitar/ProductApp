package com.example.productapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.productapp.R
import com.example.productapp.data.local.ProductEntity
import com.example.productapp.ui.theme.CustomFont
import com.example.productapp.ui.theme.CustomFont1
import com.example.productapp.ui.theme.Purple40
import com.example.productapp.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val products by viewModel.products.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Home", fontFamily = CustomFont) },
                actions = {
                    IconButton(onClick = { navController.navigate("cart") }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(products) { product ->
                ProductItem(product, viewModel)
            }
        }
    }
}

@Composable
fun ProductItem(product: ProductEntity, viewModel: HomeViewModel) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = CustomFont
            )
            Text(
                text = "Price: ${product.price}$",
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = CustomFont1
            )

            if (product.quantity > 0) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = CardDefaults.cardColors(Purple40)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                if (product.quantity == 1)
                                    viewModel.updateQuantity(product.id, 0)
                                else
                                    viewModel.decrementQuantity(product.id)
                            },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = if (product.quantity == 1) R.drawable.baseline_delete_24 else R.drawable.baseline_remove_24),
                                contentDescription = if (product.quantity == 1) "Delete" else "Remove",
                                modifier = Modifier.size(24.dp),
                                tint = Color.White
                            )
                        }

                        Text(
                            text = "${product.quantity}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            fontFamily = CustomFont
                        )

                        IconButton(
                            onClick = { viewModel.incrementQuantity(product.id) },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.add),
                                contentDescription = "Add",
                                modifier = Modifier.size(24.dp),
                                tint = Color.White
                            )
                        }
                    }
                }
            } else {
                Button(
                    onClick = { viewModel.incrementQuantity(product.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(Purple40),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Add to Cart", fontSize = 12.sp, fontFamily = CustomFont)
                }
            }
        }
    }
}
