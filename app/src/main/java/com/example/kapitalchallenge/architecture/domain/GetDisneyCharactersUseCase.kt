package com.example.kapitalchallenge.architecture.domain

import com.example.kapitalchallenge.architecture.data.disney.DisneyCharactersData
import com.example.kapitalchallenge.architecture.data.disney.DisneyCharactersRespose
import javax.inject.Inject

class GetDisneyCharactersUseCase @Inject constructor(
    private val repository: DisneyCharactersRepository
)  {

    suspend operator fun invoke(isRefresh: Boolean = false): GetDisneyCharactersResult {
        return try {
            repository.fetchDisneyCharacters(isRefresh)
            GetDisneyCharactersResult.Success
        } catch (e: Exception) {
            GetDisneyCharactersResult.Error(e.message)
        }
    }

    sealed class GetDisneyCharactersResult {
        object Success : GetDisneyCharactersResult()
        data class Error(val error: String?) : GetDisneyCharactersResult()
    }
}