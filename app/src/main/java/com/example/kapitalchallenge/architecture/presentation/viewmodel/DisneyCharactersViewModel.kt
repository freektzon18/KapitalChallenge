package com.example.kapitalchallenge.architecture.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kapitalchallenge.architecture.domain.DisneyCharactersObserveUseCase
import com.example.kapitalchallenge.architecture.domain.DisneyCharactersRepository
import com.example.kapitalchallenge.architecture.domain.GetDisneyCharactersUseCase
import com.example.kapitalchallenge.architecture.presentation.state.DisneyCharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisneyCharactersViewModel @Inject constructor(
    private val getDisneyCharactersUseCase: GetDisneyCharactersUseCase,
    observeDisneyCharactersUseCase: DisneyCharactersObserveUseCase,
    private val repository: DisneyCharactersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DisneyCharactersState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            observeDisneyCharactersUseCase().collect { list ->
                _uiState.update { it.copy(items = list) }
            }
        }
        getDisneyCharacters()
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            repository.toggleFavorite(id)
        }
    }
    fun getDisneyCharacters() {
        if (_uiState.value.isLoading) return

        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            when (val response = getDisneyCharactersUseCase(isRefresh = false)) {
                is GetDisneyCharactersUseCase.GetDisneyCharactersResult.Success -> {
                    _uiState.update { it.copy(isLoading = false) }
                }
                is GetDisneyCharactersUseCase.GetDisneyCharactersResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = response.error) }
                }
            }
        }
    }

    fun getFavoritesSize(): Int {
        var size = 0
        viewModelScope.launch {
            repository.getFavoritesFlow().collect{
                size = it.size
            }
        }
        return size
    }
}