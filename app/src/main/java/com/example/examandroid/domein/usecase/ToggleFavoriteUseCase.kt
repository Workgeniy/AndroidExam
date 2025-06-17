package com.example.examandroid.domein.usecase

import com.example.examandroid.domein.model.CatImage
import com.example.examandroid.domein.repository.CatRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(private val repository: CatRepository) {
    suspend operator fun invoke(cat:  CatImage, isFavorite: Boolean) {
        if (isFavorite) repository.addToFavorites(cat)
        else repository.removeFromFavorites(cat)
    }
}