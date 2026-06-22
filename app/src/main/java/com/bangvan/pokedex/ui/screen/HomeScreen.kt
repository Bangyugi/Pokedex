package com.bangvan.pokedex.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bangvan.pokedex.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen (viewModel: HomeViewModel){
    val pokemonList by viewModel.pokemonNames.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(pokemonList){
            name ->
            Text(
                text = name.replaceFirstChar { it.uppercase() },
                modifier = Modifier.padding(16.dp)
            )
            HorizontalDivider()
        }
    }
}