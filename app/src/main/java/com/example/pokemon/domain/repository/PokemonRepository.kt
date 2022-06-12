package com.example.pokemon.domain.repository

import com.example.pokemon.data.remote.response.PokemonListResponse
import com.example.pokemon.utils.Resource

interface PokemonRepository {
    suspend fun getPokemonList(): Resource<PokemonListResponse>
}