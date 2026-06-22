package com.bangvan.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bangvan.pokedex.ui.screen.HomeScreen
import com.bangvan.pokedex.ui.screen.SplashScreen
import com.bangvan.pokedex.ui.viewmodel.HomeViewModel
import com.bangvan.pokedex.ui.viewmodel.SplashViewModel

@Composable
fun PokedexNavGraph (modifier: Modifier = Modifier){
    val navController = rememberNavController()

    NavHost (
        navController = navController,
        startDestination = "splash_screen",
        modifier = modifier
    ){
        composable("splash_screen"){
            val splashViewModel: SplashViewModel = hiltViewModel()
            SplashScreen(
                viewModel = splashViewModel,
                onNavigateToHome = {
                    navController.navigate("home_screen"){
                        popUpTo("splash_screen"){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable("home_screen") {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(viewModel = homeViewModel)
        }
    }
}