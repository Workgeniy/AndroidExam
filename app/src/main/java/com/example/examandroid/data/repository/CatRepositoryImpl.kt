package com.example.examandroid.data.repository

import com.example.examandroid.data.remote.CatApiService
import com.example.examandroid.data.remote.toDomain
import com.example.examandroid.domein.model.CatImage
import com.example.examandroid.domein.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val api: CatApiService
) : CatRepository {

    private val favorites = mutableListOf<CatImage>()
    private val lock = Any()

    override suspend fun getCats(limit: Int): List<CatImage> = withContext(Dispatchers.IO) {
        api.getCats(limit).map { it.toDomain() }
    }

    override suspend fun addToFavorites(cat: CatImage) = withContext(Dispatchers.IO) {
        synchronized(lock) {
            if (favorites.none { it.id == cat.id }) {
                favorites.add(cat)
            }
        }
    }

    override suspend fun removeFromFavorites(cat: CatImage) = withContext(Dispatchers.IO) {
        synchronized(lock) {
            favorites.removeIf { it.id == cat.id }
        }
    }

    override suspend fun getFavorites(): List<CatImage> = withContext(Dispatchers.IO) {
        synchronized(lock) {
            favorites.toList()
        }
    }
}
