package com.example.kapitalchallenge.architecture.presentation.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kapitalchallenge.architecture.presentation.components.DisneyCharactersError
import com.example.kapitalchallenge.architecture.presentation.components.DisneyToolbar
import com.example.kapitalchallenge.architecture.presentation.components.PremiumLoader
import com.example.kapitalchallenge.architecture.presentation.viewmodel.DisneyCharactersViewModel

@Composable
fun DisneyCharactersListScreen(
    modifier: Modifier,
    viewModel: DisneyCharactersViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        DisneyToolbar(
            onFavoritesClick = {
            }
        )

        if (state.value.items.isNotEmpty()) {
            DisneyCharactersListView(
                characters = state.value.items,
                onLoadMore = {
                    viewModel.getDisneyCharacters()
                },
                onFavoriteClick = {
                    viewModel.toggleFavorite(it)
                }
            )
        }

        if (state.value.isLoading && state.value.items.isEmpty()) {
            PremiumLoader()
        }
        if (state.value.error != null) {
            DisneyCharactersError(
                message = state.value.error ?: "Unknown error",
                onRetry = {
                    viewModel.getDisneyCharacters()
                }
            )
        }
    }
}