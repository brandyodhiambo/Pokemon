package com.example.pokemon.domain.usecases

import com.example.pokemon.data.remote.responses.Pokemon
import com.example.pokemon.data.repository.RepositoryImpl
import com.example.pokemon.utils.Resource

class PokemonInfoUseCase(private val repositoryImpl: RepositoryImpl) {

    suspend fun getPokemonInfo(name:String):Resource<Pokemon>{
        return repositoryImpl.getPokemonInfo(name)
    }
}


