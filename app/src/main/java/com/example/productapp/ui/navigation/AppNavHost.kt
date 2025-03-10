package com.example.productapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productapp.ui.screens.CartScreen
import com.example.productapp.ui.screens.HomeScreen


@Composable
fun AppNavHost() {
    val navController = rememberNavController()


    Scaffold(
        bottomBar = {BottomNavigationBar(navController)}
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = androidx.compose.ui.Modifier.padding(paddingValues) // Отступ для BottomBar
        ) {
            composable(route = BottomNavItem.Home.route) { HomeScreen(navController) }
            composable(route = BottomNavItem.Cart.route) { CartScreen(navController) }
        }
    }

}