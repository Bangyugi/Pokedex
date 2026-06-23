package com.bangvan.pokedex.data.remote

import com.bangvan.pokedex.data.model.PokemonDetailDto
import com.bangvan.pokedex.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemon(
        @Query("limit")
        limit: Int = 100000,
        @Query("offset")
        offset: Int = 0
    ): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailDto

}