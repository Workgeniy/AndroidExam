package com.example.examandroid.network

import retrofit2.http.GET
import retrofit2.http.Query

data class CatImage(
    val id: String,
    val url: String
)

interface CatApiService {
    @GET("v1/images/search")
    suspend fun getCats(@Query("limit") limit: Int = 20): List<CatImage>
}