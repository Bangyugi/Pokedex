package com.bangvan.pokedex.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangvan.pokedex.data.local.entity.PokemonDetailEntity
import com.bangvan.pokedex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface DetailUiState {
    object Loading : DetailUiState
    data class Success(val pokemon: PokemonDetailEntity) : DetailUiState
    data class Error(val message: String) : DetailUiState
}

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokemonRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pokemonName: String = savedStateHandle.get<String>("pokemonName") ?: ""

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState

    init {
        loadAndSync()
    }

    fun loadAndSync() {
        _uiState.value = DetailUiState.Loading
        
        // 1. Lắng nghe thay đổi dữ liệu từ Local DB
        viewModelScope.launch {
            repository.getPokemonDetailFlow(pokemonName).collect { localData ->
                if (localData != null) {
                    _uiState.value = DetailUiState.Success(localData)
                } else {
                    if (_uiState.value !is DetailUiState.Error) {
                        _uiState.value = DetailUiState.Loading
                    }
                }
            }
        }

        // 2. Đồng bộ dữ liệu từ API về Local DB
        viewModelScope.launch {
            try {
                repository.syncPokemonDetail(pokemonName)
            } catch (e: Exception) {
                if (_uiState.value !is DetailUiState.Success) {
                    _uiState.value = DetailUiState.Error(
                        e.localizedMessage ?: "Lỗi kết nối mạng và không có dữ liệu ngoại tuyến."
                    )
                }
            }
        }
    }
}