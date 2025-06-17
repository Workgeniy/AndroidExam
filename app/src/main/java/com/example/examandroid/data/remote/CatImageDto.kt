package com.example.examandroid.data.remote

import com.example.examandroid.domein.model.CatImage

data class CatImageDto(val id: String, val url: String)

fun CatImageDto.toDomain(): CatImage = CatImage(id, url)