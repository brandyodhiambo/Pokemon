package com.example.pokemon.data.datasource

import com.example.pokemon.data.remote.responses.Pokemon
import com.example.pokemon.data.remote.responses.PokemonList

interface BasePokeDataSource {

    suspend fun getPokemonList(limit:Int,offset:Int): PokemonList
    suspend fun getPokemonInfo(name:String): Pokemon

}