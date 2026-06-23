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


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokemonRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pokemonName: String = savedStateHandle.get<String>("pokemonName") ?: ""

    private val _pokemonDetail = MutableStateFlow<PokemonDetailEntity?>(null)
    val pokemonDetail: StateFlow<PokemonDetailEntity?> = _pokemonDetail

    init {
        fetchDetail()
    }

    private fun fetchDetail(){
        viewModelScope.launch { repository.getPokemonDetail(pokemonName).collect { detail ->
            _pokemonDetail.value = detail
        } }
    }
}