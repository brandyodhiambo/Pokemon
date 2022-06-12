package com.example.pokemon.presentation.screens.pokemonList

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.pokemon.data.models.PokemonListEntry
import com.example.pokemon.domain.usecases.PokemonListUseCase
import com.example.pokemon.utils.Constants.PAGE_SIZE
import com.example.pokemon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase
) : ViewModel() {

    private var currentPage = 0

    var pokemonList = mutableStateOf<List<PokemonListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadPokemonPaginated()
    }



    // TODO:
    fun loadPokemonPaginated(){
        viewModelScope.launch {
            isLoading.value = true
            val result = pokemonListUseCase.getPokemonList(PAGE_SIZE,currentPage * PAGE_SIZE)
            Timber.d("content of pokemonList ${result}")

            when(result){
                is Resource.Success->{
                    endReached.value = currentPage* PAGE_SIZE >= result.data!!.count
                    val pokemonEntries = result.data.results.mapIndexed { _, entry ->
                        val number = if(entry.url.endsWith("/")){
                            entry.url.dropLast(1).takeLastWhile {
                                it.isDigit()
                            }
                        }else{
                            entry.url.takeLastWhile {
                                it.isDigit()
                            }
                        }
                        val url = "https://raw.githubusercontent.com/PokeApi/sprites/master/sprites/pokemon/${number}.png"
                        PokemonListEntry(entry.name.capitalize(Locale.ROOT),url,number.toInt())
                    }
                    currentPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value += pokemonEntries
                    Timber.d("content of pokemonList ${pokemonList.value}")


                }
                is Resource.Failure ->{
                    loadError.value = result.message!!
                    isLoading.value = false
                    Timber.d("content of pokemonList ${pokemonList.value}")

                }
                else -> {}
            }
        }

    }

    fun calDominantColor(drawable: Drawable,onFinish: (Color)-> Unit){
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888,true)

        Palette.from(bmp).generate{ palette->
            palette?.dominantSwatch?.rgb?.let{ colorValue->
                onFinish(Color(colorValue))
            }
        }
    }
}