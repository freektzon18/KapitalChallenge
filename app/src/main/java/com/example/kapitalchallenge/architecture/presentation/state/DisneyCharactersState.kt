package com.example.kapitalchallenge.architecture.presentation.state

import com.example.kapitalchallenge.architecture.data.disney.DisneyCharactersData

data class DisneyCharactersState(
    val items: List<DisneyCharactersData> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val endReached: Boolean = false
)
