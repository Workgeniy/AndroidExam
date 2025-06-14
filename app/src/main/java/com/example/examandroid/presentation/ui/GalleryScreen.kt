package com.example.examandroid.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.examandroid.db.AppDatabase
import com.example.examandroid.viewmodel.CatViewModel
import com.example.examandroid.viewmodel.CatViewModelFactory

@Composable
fun GalleryScreen() {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val dao = db.favoriteDao()
    val factory = remember { CatViewModelFactory(dao) }
    val viewModel: CatViewModel = viewModel(factory = factory)

    val state = viewModel.state
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { index ->
                val total = state.images.size
                if (index != null && index >= total - 3) {
                    viewModel.loadMoreCats()
                }
            }
    }

    when {
        state.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.error != null && state.images.isEmpty() -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Ошибка: ${state.error}")
            }
        }

        else -> {
            LazyColumn(state = listState) {
                items(state.images) { image ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column {
                            Image(
                                painter = rememberAsyncImagePainter(image.url),
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
                                    viewModel.toggleFavorite(image)
                                }) {
                                    val isFavorite = remember { mutableStateOf(false) }

                                    // check current state of favorite
                                    LaunchedEffect(Unit) {
                                        viewModel.getFavorites { list ->
                                            isFavorite.value = list.any { it.id == image.id }
                                        }
                                    }

                                    Icon(
                                        imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                        contentDescription = "Favorite",
                                        tint = if (isFavorite.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }

                item {
                    if (state.isLoading) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}
