package com.example.examandroid.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examandroid.domein.model.CatImage
import com.example.examandroid.domein.usecase.GetCatsUseCase
import com.example.examandroid.domein.usecase.GetFavoritesUseCase
import com.example.examandroid.domein.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val getCats: GetCatsUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase,
    private val getFavorites: GetFavoritesUseCase
) : ViewModel() {

    private val _cats = MutableStateFlow<List<CatImage>>(emptyList())
    val cats: StateFlow<List<CatImage>> get() = _cats

    private var currentPage = 0
    private val limit = 20
    private var isLoading = false

    fun loadCats(limit: Int = this.limit) {
        viewModelScope.launch {
            isLoading = true
            val newCats = getCats(limit)
            _cats.value = newCats
            currentPage = 1
            isLoading = false
        }
    }

    fun loadMoreCats() {
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true
            val newCats = getCats(limit)
            _cats.value = _cats.value + newCats
            currentPage++
            isLoading = false
        }
    }

    fun toggleFavorite(cat: CatImage, isFavorite: Boolean) {
        viewModelScope.launch {
            try {
                toggleFavorite(cat, isFavorite)
            } catch (e: Exception) {
                Log.e("CatViewModel", "Ошибка при избранном: ${e.message}")
            }
        }
    }

    fun getFavorites(onResult: (List<CatImage>) -> Unit) {
        viewModelScope.launch {
            onResult(getFavorites())
        }
    }
}
