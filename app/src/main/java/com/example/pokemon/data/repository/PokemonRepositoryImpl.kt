package com.example.pokemon.data.repository

import com.example.pokemon.data.datasource.PokeDataSource
import com.example.pokemon.data.remote.response.PokemonListResponse
import com.example.pokemon.domain.repository.PokemonRepository
import com.example.pokemon.utils.Resource
import java.lang.Exception

class PokemonRepositoryImpl(private val pokeDataSource: PokeDataSource): PokemonRepository {
    override suspend fun getPokemonList(): Resource<PokemonListResponse> {
        return try {
            val response = pokeDataSource.getPokemonList()
            Resource.Success(data = response)
        }catch (e:Exception){
            return  Resource.Failure("OOPS! Something Went wrong")
        }
    }
}