package com.example.kapitalchallenge.architecture.presentation.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.kapitalchallenge.architecture.data.disney.DisneyCharactersData

@Composable
fun DisneyCharactersListView(
    characters: List<DisneyCharactersData>,
    onLoadMore: () -> Unit,
    onFavoriteClick: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(
            items = characters,
            key = { _, character -> character.Id }
        ) { index, character ->

            if (index >= characters.size - 3) {
                onLoadMore()
            }

            DisneyCharactersListItem(character, onFavoriteClick = onFavoriteClick)
        }
    }
}