package com.example.examandroid.di

import com.example.examandroid.data.remote.CatApiService
import com.example.examandroid.data.repository.CatRepositoryImpl
import com.example.examandroid.domein.repository.CatRepository
import com.example.examandroid.domein.usecase.GetCatsUseCase
import com.example.examandroid.domein.usecase.GetFavoritesUseCase
import com.example.examandroid.domein.usecase.ToggleFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideCatApi(retrofit: Retrofit): CatApiService =
        retrofit.create(CatApiService::class.java)

    @Provides
    @Singleton
    fun provideCatRepository(api: CatApiService): CatRepository =
        CatRepositoryImpl(api)

    @Provides
    fun provideGetCatsUseCase(repository: CatRepository) = GetCatsUseCase(repository)

    @Provides
    fun provideToggleFavoriteUseCase(repository: CatRepository) = ToggleFavoriteUseCase(repository)

    @Provides
    fun provideGetFavoritesUseCase(repository: CatRepository) = GetFavoritesUseCase(repository)
}