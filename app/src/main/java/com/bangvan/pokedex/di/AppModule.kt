package com.bangvan.pokedex.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bangvan.pokedex.data.local.PokemonDao
import com.bangvan.pokedex.data.local.PokemonDatabase
import com.bangvan.pokedex.data.remote.PokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{

        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonApi(
        retrofit: Retrofit
    ): PokemonApi{
        return retrofit.create(
            PokemonApi::class.java
        )
    }

    @Provides
    @Singleton
    fun providePokemonDatabase(@ApplicationContext context: Context): PokemonDatabase {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            "pokemon_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(db: PokemonDatabase): PokemonDao {
        return db.pokemonDao()
    }
}