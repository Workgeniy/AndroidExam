package com.example.examandroid.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.examandroid.model.FavoriteCat
import com.example.examandroid.network.CatImage
import com.example.examandroid.viewmodel.CatViewModel


@Composable
fun FavoritesScreen(viewModel: CatViewModel) {
    val favorites = remember { mutableStateListOf<FavoriteCat>() }


    LaunchedEffect(Unit) {
        viewModel.getFavorites {
            favorites.clear()
            favorites.addAll(it)
        }
    }

    LazyColumn {
        items(favorites) { cat ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column {
                    Image(
                        painter = rememberAsyncImagePainter(cat.url),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.End
                    ) {


                        IconButton(onClick = {
                            viewModel.toggleFavorite(cat.toCatImage())

                            viewModel.getFavorites {
                                favorites.clear()
                                favorites.addAll(it)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Удалить из избранного",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }
    }
}

fun FavoriteCat.toCatImage(): CatImage {
    return CatImage(id = this.id, url = this.url)
}
