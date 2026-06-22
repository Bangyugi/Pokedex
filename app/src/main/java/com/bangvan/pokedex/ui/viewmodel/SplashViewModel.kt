package com.bangvan.pokedex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangvan.pokedex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _isSyncing = MutableStateFlow(true)
    val isSyncing: StateFlow<Boolean> = _isSyncing

    init {
        viewModelScope.launch {
            repository.syncDataFromApi()
            _isSyncing.value = false
        }
    }
}