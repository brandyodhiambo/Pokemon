package com.example.pokemon.presentation.screens.pokemonList

import com.example.pokemon.domain.models.Pokemon

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemonList: List<Pokemon> = emptyList(),
    val loadError: String? = null
)
