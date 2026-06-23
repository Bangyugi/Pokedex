package com.bangvan.pokedex.data.repository

import android.util.Log
import com.bangvan.pokedex.data.local.dao.PokemonDao
import com.bangvan.pokedex.data.local.entity.PokemonDetailEntity
import com.bangvan.pokedex.data.local.entity.PokemonEntity
import com.bangvan.pokedex.data.remote.PokemonApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonApi,
    private val dao: PokemonDao
) {
    val pokemons: Flow<List<PokemonEntity>> = dao.getAllPokemons()

    suspend fun syncDataFromApi() {
        try {
            val response = api.getPokemon()
            val entities = response.results.map { dto ->
                PokemonEntity(name = dto.name, url = dto.url)
            }
            dao.insertPokemons(entities)
            Log.d("Pokemon", "Sync thành công: Đã lưu vào DB")
        } catch (e: Exception) {
            Log.e("Pokemon", "Lỗi Sync API (Dùng cache DB): ${e.message}")
        }
    }

    fun getPokemonDetail (name:String): Flow<PokemonDetailEntity> = flow {
        val localData = dao.getPokemonDetail(name)
        if(localData != null){
            emit(localData)
        }
        else{
            try {
                val remoteData = api.getPokemonDetail(name)
                val entity = remoteData.toEntity()
                dao.insertPokemonDetail(entity)
                emit(entity)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}