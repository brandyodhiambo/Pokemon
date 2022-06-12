package com.example.pokemon.domain

import com.example.pokemon.data.remote.responses.Pokemon
import com.example.pokemon.data.remote.responses.PokemonList
import com.example.pokemon.utils.Resource

interface Repository {
    suspend fun getPokemonList(limit:Int,offset:Int): Resource<PokemonList>
    suspend fun getPokemonInfo(name:String): Resource<Pokemon>
}