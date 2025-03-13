package com.example.productapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productapp.ui.screens.CartScreen
import com.example.productapp.ui.screens.HomeScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(), // Оставляем верхний отступ
                    bottom = 0.dp // Убираем нижний отступ, чтобы не дублировать BottomNavigationBar
                )
                .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal)) // Учитываем навигационные жесты
        ) {
            composable(route = BottomNavItem.Home.route) { HomeScreen(navController) }
            composable(route = BottomNavItem.Cart.route) { CartScreen(navController) }
        }
    }
}