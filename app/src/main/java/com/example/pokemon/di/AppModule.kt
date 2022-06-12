package com.example.pokemon.di

import com.example.pokemon.data.datasource.PokeDataSource
import com.example.pokemon.data.remote.PokeApi
import com.example.pokemon.data.repository.RepositoryImpl
import com.example.pokemon.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //provide pokeApi
    @Provides
    @Singleton
    fun providePokeApi() :PokeApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApi::class.java)
    }

    //provides datasource
    @Provides
    @Singleton
    fun providePokeDataSource(pokeApi: PokeApi):PokeDataSource{
        return PokeDataSource(pokeApi)
    }

    //provide repository
    @Provides
    @Singleton
    fun providePokeRepository(pokeDataSource: PokeDataSource): RepositoryImpl{
        return RepositoryImpl(pokeDataSource)
    }

}