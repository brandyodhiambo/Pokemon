package com.example.pokemon.data.remote.response


import com.google.gson.annotations.SerializedName

data class PokemonResult(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)