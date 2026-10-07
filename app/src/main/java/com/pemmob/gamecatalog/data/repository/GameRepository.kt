package com.pemmob.gamecatalog.data.repository

import com.pemmob.gamecatalog.data.model.GameDto
import com.pemmob.gamecatalog.data.network.ApiService
import com.pemmob.gamecatalog.data.network.RetrofitClient

class GameRepository(
    private val apiService: ApiService = RetrofitClient.apiService
) {
    suspend fun getGames(search: String? = null): Result<List<GameDto>> {
        return try {
            val response = apiService.getGames(
                apiKey = RetrofitClient.apiKey,
                search = search
            )
            Result.success(response.results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getGameDetail(id: Int): Result<GameDto> {
        return try {
            val game = apiService.getGameDetail(
                id = id,
                apiKey = RetrofitClient.apiKey
            )
            Result.success(game)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
