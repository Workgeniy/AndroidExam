package com.example.examandroid.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteCat(
    @PrimaryKey val id: String,
    val url: String
)