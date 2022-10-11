package com.jeckonly.core_model.dto.pokemonlistitem

data class PokemonInfoDto(
    val name: String,
    val url: String
) {
    val id: Int = getIdFromUrl(url = url)

    private fun getIdFromUrl(url: String): Int{
        val tempList = url.split("/")
        return (tempList[tempList.size - 2]).toInt()
    }
}