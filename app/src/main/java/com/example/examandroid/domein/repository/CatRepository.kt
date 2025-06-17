package com.example.examandroid.domein.repository

import com.example.examandroid.domein.model.CatImage

interface CatRepository {
    suspend fun getCats(limit: Int): List<CatImage>
    suspend fun addToFavorites(cat: CatImage)
    suspend fun removeFromFavorites(cat: CatImage): Boolean
    suspend fun getFavorites(): List<CatImage>
}