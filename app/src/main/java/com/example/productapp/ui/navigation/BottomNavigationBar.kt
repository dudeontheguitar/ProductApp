package com.example.productapp.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    // Отслеживаем текущее состояние навигационного стека
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val screens = listOf(
        BottomNavItem.Home,
        BottomNavItem.Cart
    )

    NavigationBar(
        containerColor = Color.White // Фон BottomNavigationBar
    ) {
        screens.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title,
                        tint = if (isSelected) Color.Black else Color.Gray // Черный для активного, серый для неактивного
                    )
                },
                label = {
                    Text(
                        text = screen.title,
                        color = if (isSelected) Color.Black else Color.Gray
                    )
                },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent // Убираем фон под иконкой
                )
            )
        }
    }
}
