package com.example.pokemon.data.datasource

import com.example.pokemon.data.remote.PokeApi
import com.example.pokemon.data.remote.response.PokemonListResponse

class PokeDataSourceImpl(private val pokeApi: PokeApi) :PokeDataSource {
    override suspend fun getPokemonList(): PokemonListResponse {
       return pokeApi.getPokemonList()
    }
}