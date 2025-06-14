package com.example.examandroid.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.examandroid.model.FavoriteCat



@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    suspend fun getAll(): List<FavoriteCat>

    @Query("SELECT * FROM favorites WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): FavoriteCat?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cat: FavoriteCat)

    @Delete
    suspend fun delete(cat: FavoriteCat)
}