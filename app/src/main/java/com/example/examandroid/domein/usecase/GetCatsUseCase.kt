package com.example.examandroid.domein.usecase

import com.example.examandroid.domein.repository.CatRepository

class GetCatsUseCase(private val repository: CatRepository) {
    suspend operator fun invoke(limit: Int) = repository.getCats(limit)
}