package com.example.kapitalchallenge.architecture.domain

import javax.inject.Inject

class DisneyCharactersObserveUseCase @Inject constructor(
    private val repository: DisneyCharactersRepository
) {
    operator fun invoke() = repository.getCharactersFlow()
}