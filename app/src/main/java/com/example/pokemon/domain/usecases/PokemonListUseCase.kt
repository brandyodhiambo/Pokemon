package com.example.pokemon.domain.usecases

import com.example.pokemon.data.remote.responses.PokemonList
import com.example.pokemon.data.repository.RepositoryImpl
import com.example.pokemon.utils.Resource

class PokemonListUseCase(private val repositoryImpl: RepositoryImpl) {
    suspend fun getPokemonList(limit:Int,offset:Int):Resource<PokemonList>{
        return repositoryImpl.getPokemonList(limit,offset)
    }
}

