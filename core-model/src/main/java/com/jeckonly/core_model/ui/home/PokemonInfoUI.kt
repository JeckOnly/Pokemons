package com.jeckonly.core_model.ui.home

data class PokemonInfoUI(
    val name: String,
    val url: String,
    val id: Int
) {

    val artworkUrl: String = getArtWorkUrlFormId(id)

    private fun getArtWorkUrlFormId(id: Int): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    }
}
