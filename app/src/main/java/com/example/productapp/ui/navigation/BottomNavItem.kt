package com.example.productapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Home : BottomNavItem("home", "Home", Icons.Filled.Home)
    object Cart : BottomNavItem("cart", "Cart", Icons.Filled.ShoppingCart)
}