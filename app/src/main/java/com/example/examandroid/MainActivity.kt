package com.example.examandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.examandroid.db.AppDatabase
import com.example.examandroid.presentation.ui.BottomNavigationBar
import com.example.examandroid.presentation.ui.MyAppTheme
import com.example.examandroid.presentation.ui.NavigationHost
import com.example.examandroid.viewmodel.CatViewModel
import com.example.examandroid.viewmodel.CatViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val db = AppDatabase.getDatabase(applicationContext)
        val factory = CatViewModelFactory(db.favoriteDao())
        val viewModel = ViewModelProvider(this, factory)[CatViewModel::class.java]


        super.onCreate(savedInstanceState)

        setContent {
            MyAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { padding ->
                    NavigationHost(
                        navController = navController,
                        modifier = Modifier.padding(padding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}