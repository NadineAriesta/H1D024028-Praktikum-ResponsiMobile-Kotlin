package com.pemmob.gamecatalog.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pemmob.gamecatalog.ui.screen.GameDetailScreen
import com.pemmob.gamecatalog.ui.screen.HomeScreen
import com.pemmob.gamecatalog.ui.viewmodel.GameViewModel

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail/{gameId}") {
        fun createRoute(gameId: Int) = "detail/$gameId"
    }
}

@Composable
fun GameNavGraph(
    viewModel: GameViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            val gamesState by viewModel.gamesState.collectAsState()
            val searchQuery by viewModel.searchQuery.collectAsState()

            HomeScreen(
                gamesState = gamesState,
                searchQuery = searchQuery,
                currentApiKey = viewModel.apiKey,
                onSearchQueryChanged = { query ->
                    viewModel.updateSearchQuery(query)
                },
                onApiKeyChanged = { newKey ->
                    viewModel.updateApiKey(newKey)
                },
                onGameClick = { gameId ->
                    navController.navigate(Screen.Detail.createRoute(gameId))
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("gameId") { type = NavType.IntType })
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getInt("gameId") ?: 0
            
            LaunchedEffect(gameId) {
                viewModel.fetchGameDetail(gameId)
            }

            val detailState by viewModel.detailState.collectAsState()

            GameDetailScreen(
                detailState = detailState,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
