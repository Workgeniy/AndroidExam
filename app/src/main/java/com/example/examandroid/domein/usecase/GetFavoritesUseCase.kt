package com.example.examandroid.domein.usecase

import com.example.examandroid.domein.repository.CatRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: CatRepository
) {
    suspend operator fun invoke() = repository.getFavorites()

}

