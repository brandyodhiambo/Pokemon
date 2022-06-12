package com.example.pokemon.data.repository

import com.example.pokemon.data.datasource.PokeDataSource
import com.example.pokemon.data.remote.responses.Pokemon
import com.example.pokemon.data.remote.responses.PokemonList
import com.example.pokemon.domain.Repository
import com.example.pokemon.utils.Resource
import com.example.pokemon.utils.SafeApiCall

class RepositoryImpl(private val pokeDataSource: PokeDataSource):Repository,SafeApiCall() {
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        safeApiCall {
            val response = pokeDataSource.getPokemonList(limit,offset)
            Resource.Success(response)
        }
        return  Resource.Failure("OOPS! Something Went wrong")
    }

    override suspend fun getPokemonInfo(name: String): Resource<Pokemon> {
        safeApiCall {
            val response = pokeDataSource.getPokemonInfo(name)
            Resource.Success(response)
        }
        return  Resource.Failure("OOPS! Something Went wrong")
    }
}