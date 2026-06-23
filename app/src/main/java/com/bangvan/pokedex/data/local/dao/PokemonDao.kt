package com.bangvan.pokedex.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangvan.pokedex.data.local.entity.PokemonDetailEntity
import com.bangvan.pokedex.data.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table")
    fun getAllPokemons(): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon_detail WHERE name = :name")
    suspend fun getPokemonDetail(name: String): PokemonDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonDetail(pokemonDetail: PokemonDetailEntity)
}