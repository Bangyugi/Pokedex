package com.bangvan.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bangvan.pokedex.ui.screen.DetailScreen
import com.bangvan.pokedex.ui.screen.HomeScreen
import com.bangvan.pokedex.ui.screen.SplashScreen
import com.bangvan.pokedex.ui.viewmodel.DetailViewModel
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
            HomeScreen(viewModel = homeViewModel,
                onNavigateToDetail = { pokemonName ->
                    navController.navigate("detail_screen/$pokemonName")
                }
            )
        }

        composable(
            route = "detail_screen/{pokemonName}",
            arguments = listOf(
                navArgument("pokemonName") { type = NavType.StringType }
            )
        ) {
            val detailViewModel: DetailViewModel = hiltViewModel()
            DetailScreen(
                viewModel = detailViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}