package com.jeckonly.core_model.dto.pokemonlistitem

import android.util.Log

data class PokemonInfoDto(
    val name: String,
    val url: String
) {
    fun getIdFromUrl(url: String): Int{
        Log.d("PokemonInfoDto", "getIdFromUrl")
        val tempList = url.split("/")
        return (tempList[tempList.size - 2]).toInt()
    }
}

fun main() {
    val url = "https://pokeapi.co/api/v2/pokemon/4/"
    val tempList = url.split("/")
    print((tempList[tempList.size - 2]).toInt())
}