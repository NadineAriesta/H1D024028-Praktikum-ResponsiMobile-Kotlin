package com.pemmob.gamecatalog.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemmob.gamecatalog.data.model.GameDto
import com.pemmob.gamecatalog.data.network.RetrofitClient
import com.pemmob.gamecatalog.data.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}

class GameViewModel(
    private val repository: GameRepository = GameRepository()
) : ViewModel() {

    private val _gamesState = MutableStateFlow<UiState<List<GameDto>>>(UiState.Loading)
    val gamesState: StateFlow<UiState<List<GameDto>>> = _gamesState.asStateFlow()

    private val _detailState = MutableStateFlow<UiState<GameDto>>(UiState.Loading)
    val detailState: StateFlow<UiState<GameDto>> = _detailState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    var apiKey: String = RetrofitClient.apiKey
        private set

    init {
        fetchGames()
    }

    fun updateApiKey(newKey: String) {
        apiKey = newKey.trim()
        RetrofitClient.apiKey = apiKey
        fetchGames(_searchQuery.value.ifBlank { null })
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        fetchGames(query.ifBlank { null })
    }

    fun fetchGames(search: String? = null) {
        viewModelScope.launch {
            _gamesState.value = UiState.Loading
            val result = repository.getGames(search)
            if (result.isSuccess) {
                _gamesState.value = UiState.Success(result.getOrDefault(emptyList()))
            } else {
                _gamesState.value = UiState.Error(
                    result.exceptionOrNull()?.localizedMessage ?: "Terjadi kesalahan saat memuat data"
                )
            }
        }
    }

    fun fetchGameDetail(id: Int) {
        viewModelScope.launch {
            _detailState.value = UiState.Loading
            val result = repository.getGameDetail(id)
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _detailState.value = UiState.Success(it)
                } ?: run {
                    _detailState.value = UiState.Error("Detail game tidak ditemukan")
                }
            } else {
                _detailState.value = UiState.Error(
                    result.exceptionOrNull()?.localizedMessage ?: "Terjadi kesalahan saat memuat detail game"
                )
            }
        }
    }
}
