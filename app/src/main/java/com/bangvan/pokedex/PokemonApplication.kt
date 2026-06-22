package com.bangvan.pokedex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokemonApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        android.util.Log.d("Pokemon", "PokemonApplication đã khởi chạy!")
    }
}