package com.example.examandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examandroid.db.FavoriteDao

class CatViewModelFactory(
    private val favoriteDao: FavoriteDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatViewModel::class.java)) {
            return CatViewModel(favoriteDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}