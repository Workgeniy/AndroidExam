package com.example.examandroid.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {
    @GET("v1/images/search")
    suspend fun getCats(@Query("limit") limit: Int): List<CatImageDto>
}