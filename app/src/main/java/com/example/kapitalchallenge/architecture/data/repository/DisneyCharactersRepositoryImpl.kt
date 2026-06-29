package com.example.kapitalchallenge.architecture.data.repository

import com.example.kapitalchallenge.architecture.data.local.DisneyCharacterDao
import com.example.kapitalchallenge.architecture.data.local.FavoriteEntity
import com.example.kapitalchallenge.architecture.data.net.DisneyCharactersApiServices
import com.example.kapitalchallenge.architecture.domain.DisneyCharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DisneyCharactersRepositoryImpl @Inject constructor(
    private val apiService: DisneyCharactersApiServices,
    private val dao: DisneyCharacterDao
) : DisneyCharactersRepository {

    private var currentPage = 1
    private var isLastPage = false

    override fun getCharactersFlow() =
        dao.getAllCharactersFlow()

    override fun getFavoritesFlow() =
        dao.getFavoritesFlow()

    override suspend fun toggleFavorite(id: Int) {
        dao.toggleFavorite(id)
    }

    override suspend fun fetchDisneyCharacters(
        isRefresh: Boolean
    ) {
        if (isRefresh) {
            currentPage = 1
            isLastPage = false
            dao.clearAll()
        }

        if (isLastPage) return

        val before = dao.countCharacters()

        val response =
            apiService.getDisneyCharacters(currentPage)

        if (response.data.isNotEmpty()) {
            dao.insertAll(response.data)
        }

        val after = dao.countCharacters()

        if (before == after || response.info.nextPage == null) {
            isLastPage = true
        } else {
            currentPage++
        }
    }
}