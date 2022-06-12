package com.example.pokemon.di

import com.example.pokemon.data.remote.responses.Pokemon
import com.example.pokemon.data.repository.RepositoryImpl
import com.example.pokemon.domain.usecases.PokemonInfoUseCase
import com.example.pokemon.domain.usecases.PokemonListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    //provides pokemonList UseCase
    @Provides
    @Singleton
    fun providePokemonListUseCase(repositoryImpl: RepositoryImpl):PokemonListUseCase{
        return PokemonListUseCase(repositoryImpl)
    }

    @Provides
    @Singleton
    fun providePokemonInfoUseCase(repositoryImpl: RepositoryImpl):PokemonInfoUseCase{
        return PokemonInfoUseCase(repositoryImpl)
    }
}