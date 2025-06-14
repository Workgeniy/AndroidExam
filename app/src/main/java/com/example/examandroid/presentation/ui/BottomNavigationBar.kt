package com.example.examandroid.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf("Gallery", "Favorites", "About")
    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate(screen) },
                icon = { Icon(Icons.Default.Pets, contentDescription = null) },
                label = { Text(screen) }
            )
        }
    }
}