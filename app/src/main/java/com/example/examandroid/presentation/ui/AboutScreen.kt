package com.example.examandroid.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("О приложении", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Это приложение показывает случайные изображения котиков.")
        Spacer(modifier = Modifier.height(4.dp))
        Text("API: https://thecatapi.com")
    }
}