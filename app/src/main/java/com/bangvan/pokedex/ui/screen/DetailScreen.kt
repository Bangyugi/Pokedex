package com.bangvan.pokedex.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bangvan.pokedex.ui.viewmodel.DetailViewModel


@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    onNavigateBack:() -> Unit
){

    val detail by viewModel.pokemonDetail.collectAsState()

    if (detail == null){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    else{
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Quay lại"
                    )
                }
            }

            AsyncImage(
                model = detail!!.imageUrl,
                contentDescription = detail!!.name,
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = detail!!.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Chiều cao: ${detail!!.height / 10f} m")
            Text("Cân nặng: ${detail!!.weight / 10f} kg")

            Spacer(modifier = Modifier.height(8.dp))

            Text("Hệ: ${detail!!.types.joinToString(", ")}")
        }
    }
}