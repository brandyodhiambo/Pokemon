package com.example.pokemon.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemon.data.models.PokemonListEntry
import com.example.pokemon.data.remote.PokeApi
import com.example.pokemon.data.remote.responses.PokemonList
import com.example.pokemon.utils.Constants.PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException
import java.util.*

class PokemonListPagingSource(private val pokeApi: PokeApi) :PagingSource<Int,PokemonListEntry>(){
    override fun getRefreshKey(state: PagingState<Int, PokemonListEntry>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListEntry> {
        return try {
            val nextPage = params.key?:1
            val pokemonList = pokeApi.getPokemonList(PAGE_SIZE,nextPage)
            val pokemonEntries =  pokemonList.results.mapIndexed { index, entry ->
                val number = if(entry.url.endsWith("/")){
                    entry.url.dropLast(1).takeLastWhile {
                        it.isDigit()
                    }
                } else{
                    entry.url.takeLastWhile {
                        it.isDigit()
                    }
                }
                val url = "https://raw.githubusercontent.com/PokeApi/sprites/master/sprites/pokemon/${number}.png"
                PokemonListEntry(entry.name.capitalize(Locale.ROOT),url,number.toInt())
            }
            LoadResult.Page(
                data = pokemonEntries,
                prevKey = if (nextPage == 1) null else nextPage -1,
                nextKey = if (pokemonList.results.isEmpty()) null else pokemonList.count+1

            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}

