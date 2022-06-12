package com.example.pokemon.domain.usecases

import com.example.pokemon.data.remote.response.PokemonListResponse
import com.example.pokemon.domain.repository.PokemonRepository
import com.example.pokemon.utils.Resource
import timber.log.Timber

class PokemonListUseCase(private val repository: PokemonRepository) {
    suspend fun getPokemonList():Resource<PokemonListResponse>{
        return repository.getPokemonList()
    }
}

