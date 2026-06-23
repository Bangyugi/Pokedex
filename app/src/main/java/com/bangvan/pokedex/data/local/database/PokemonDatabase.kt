package com.bangvan.pokedex.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bangvan.pokedex.data.local.Converters
import com.bangvan.pokedex.data.local.entity.PokemonEntity
import com.bangvan.pokedex.data.local.dao.PokemonDao
import com.bangvan.pokedex.data.local.entity.PokemonDetailEntity

@Database(
    entities = [PokemonEntity::class, PokemonDetailEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}