package com.example.productapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.productapp.ui.theme.Purple40
import com.example.productapp.ui.theme.PurpleGrey40
import com.example.productapp.ui.theme.Red50

@Composable
fun BottomNavigationBar(navController: NavController) {
    // Отслеживаем текущее состояние навигационного стека
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val screens = listOf(
        BottomNavItem.Home,
        BottomNavItem.Cart
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)), // Закругляем верхние углы
        color = Red50,
        shadowElevation = 8.dp
    ) {
        NavigationBar(
            containerColor = Red50 // Фон BottomNavigationBar
        ) {
            screens.forEach { screen ->
                val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = if (isSelected) screen.selectedIcon else screen.unselectedIcon,
                            contentDescription = screen.title,
                            tint = if (isSelected) Purple40 else PurpleGrey40 // Активный и неактивный цвета
                        )
                    },
                    label = {
                        Text(
                            text = screen.title,
                            color = if (isSelected) Purple40 else PurpleGrey40
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
}

