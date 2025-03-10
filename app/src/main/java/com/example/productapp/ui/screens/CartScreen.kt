package com.example.productapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ModalDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun CartScreen(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "Cart Screen")
    }
}