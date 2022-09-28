package com.jeckonly.core_model.ui

data class PokemonInfoUI(
    val name: String,
    val url: String,
) {
    val artworkUrl: String = getArtWorkUrlFormId(getIdFromUrl(url = url))

    private fun getIdFromUrl(url: String): String{
        val tempList = url.split("/")
        return tempList[tempList.size - 2]
    }

    private fun getArtWorkUrlFormId(id: String): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    }
}
