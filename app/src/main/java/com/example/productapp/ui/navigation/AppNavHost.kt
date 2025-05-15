package com.example.productapp.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productapp.ui.screens.CartScreen
import com.example.productapp.ui.screens.HomeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
        ) {
            composable(route = BottomNavItem.Home.route) { HomeScreen(navController) }
            composable(route = BottomNavItem.Cart.route) { CartScreen(navController) }
        }
    }
}
