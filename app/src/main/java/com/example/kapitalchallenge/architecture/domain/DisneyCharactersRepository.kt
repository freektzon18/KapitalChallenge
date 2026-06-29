package com.example.kapitalchallenge.architecture.domain

import com.example.kapitalchallenge.architecture.data.disney.DisneyCharactersData
import com.example.kapitalchallenge.architecture.data.disney.DisneyCharactersRespose
import kotlinx.coroutines.flow.Flow

interface DisneyCharactersRepository {

    fun getCharactersFlow(): Flow<List<DisneyCharactersData>>

    fun getFavoritesFlow(): Flow<List<DisneyCharactersData>>

    suspend fun fetchDisneyCharacters(
        isRefresh: Boolean
    )

    suspend fun toggleFavorite(id: Int)
}