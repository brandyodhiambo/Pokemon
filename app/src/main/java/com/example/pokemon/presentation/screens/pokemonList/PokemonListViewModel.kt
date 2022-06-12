package com.example.pokemon.presentation.screens.pokemonList


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.models.Pokemon
import com.example.pokemon.domain.usecases.PokemonListUseCase
import com.example.pokemon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase
) : ViewModel() {

    private val _pokemonList = mutableStateOf(PokemonListState())
    val pokemonList: State<PokemonListState> = _pokemonList

    init {
        loadPokemon()
    }

    fun loadPokemon() {
        viewModelScope.launch {
            _pokemonList.value = pokemonList.value.copy(isLoading = true)
            val result = pokemonListUseCase.getPokemonList()
            _pokemonList.value = pokemonList.value.copy(isLoading = false)
            when (result) {
                is Resource.Success -> {
                    val pokemonEntries = result.data?.results!!.mapIndexed { _, entry ->
                        val number = if (entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile {
                                it.isDigit()
                            }
                        } else {
                            entry.url.takeLastWhile {
                                it.isDigit()
                            }
                        }
                        val url =
                            "https://raw.githubusercontent.com/PokeApi/sprites/master/sprites/pokemon/${number}.png"
                        Pokemon(entry.name.capitalize(Locale.ROOT), url, number.toInt())
                    }
                    _pokemonList.value = pokemonList.value.copy(pokemonList = pokemonEntries)
                }
                is Resource.Failure -> {
                    _pokemonList.value = pokemonList.value.copy(loadError = result.message)
                }
                else -> {}
            }
        }
    }
}