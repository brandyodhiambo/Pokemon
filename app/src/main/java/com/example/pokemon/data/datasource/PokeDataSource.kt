package com.example.pokemon.data.datasource

import com.example.pokemon.data.remote.response.PokemonListResponse


interface PokeDataSource {
    suspend fun getPokemonList(): PokemonListResponse
}