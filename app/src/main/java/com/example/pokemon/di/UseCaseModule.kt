package com.example.pokemon.di

import com.example.pokemon.data.repository.PokemonRepositoryImpl
import com.example.pokemon.domain.repository.PokemonRepository
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
    fun providePokemonListUseCase(repository: PokemonRepository):PokemonListUseCase{
        return PokemonListUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePokemonInfoUseCase(repositoryImpl: PokemonRepositoryImpl):PokemonInfoUseCase{
        return PokemonInfoUseCase(repositoryImpl)
    }
}