package com.example.examandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examandroid.db.FavoriteDao
import com.example.examandroid.model.FavoriteCat
import com.example.examandroid.network.CatImage
import com.example.examandroid.network.RetrofitInstance
import kotlinx.coroutines.launch

class CatViewModel(private val favoriteDao: FavoriteDao) : ViewModel() {
    var state by mutableStateOf(CatState())
        private set

    private var isLoadingMore = false

    init {
        loadCats(20)
    }

    fun loadCats(limit: Int = 10) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val cats = RetrofitInstance.api.getCats(limit)
                state = state.copy(images = cats, isLoading = false, error = null)
            } catch (e: Exception) {
                state = state.copy(error = e.message, isLoading = false)
            }
        }
    }

    fun loadMoreCats() {
        if (isLoadingMore) return
        isLoadingMore = true

        viewModelScope.launch {
            try {
                val moreCats = RetrofitInstance.api.getCats(10)
                state = state.copy(images = state.images + moreCats)
            } catch (e: Exception) {
                state = state.copy(error = e.message)
            } finally {
                isLoadingMore = false
            }
        }
    }

    fun toggleFavorite(cat: CatImage) {
        viewModelScope.launch {
            val existing = favoriteDao.getById(cat.id)
            if (existing != null) {
                favoriteDao.delete(existing)
            } else {
                favoriteDao.insert(FavoriteCat(cat.id, cat.url))
            }
        }
    }

    fun getFavorites(onLoaded: (List<FavoriteCat>) -> Unit) {
        viewModelScope.launch {
            onLoaded(favoriteDao.getAll())
        }
    }
}

data class CatState(
    val images: List<CatImage> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)