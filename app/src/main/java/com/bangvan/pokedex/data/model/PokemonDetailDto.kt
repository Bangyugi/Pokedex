package com.bangvan.pokedex.data.model

import com.bangvan.pokedex.data.local.entity.PokemonDetailEntity

data class PokemonDetailDto(
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypeSlot>,
    val sprites: Sprites
) {
    data class TypeSlot(val type: TypeInfo)
    data class TypeInfo(val name: String)
    data class Sprites(val front_default: String)

    fun toEntity() = PokemonDetailEntity(
        name = name,
        imageUrl = sprites.front_default,
        types = types.map { it.type.name },
        height = height,
        weight = weight
    )
}