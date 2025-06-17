package com.example.examandroid.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.examandroid.presentation.viewmodel.CatViewModel


@Composable
fun NavigationHost( navController: NavHostController,
                    modifier: Modifier = Modifier,
                    viewModel: CatViewModel) {
    NavHost(navController, startDestination = "Gallery", modifier = modifier) {
        composable("Gallery") { GalleryFragment() }
        composable("About") { AboutFragment() }
        composable("favorites") { FavoriteFragment() }
    }
}