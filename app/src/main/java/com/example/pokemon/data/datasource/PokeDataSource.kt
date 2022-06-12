package com.example.pokemon.data.datasource

import com.example.pokemon.data.remote.PokeApi
import com.example.pokemon.data.remote.responses.Pokemon
import com.example.pokemon.data.remote.responses.PokemonList

class PokeDataSource(private val pokeApi: PokeApi) :BasePokeDataSource {
    override suspend fun getPokemonList(limit: Int, offset: Int): PokemonList {
       return pokeApi.getPokemonList(limit = limit, offset = offset)
    }

    override suspend fun getPokemonInfo(name: String): Pokemon {
        return pokeApi.getPokemonInfo(name = name)
    }

}