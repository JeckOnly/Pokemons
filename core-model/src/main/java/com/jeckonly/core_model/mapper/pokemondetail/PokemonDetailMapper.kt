package com.jeckonly.core_model.mapper.pokemondetail

import com.jeckonly.core_model.dto.pokemondetail.PokemonDetailDto
import com.jeckonly.core_model.dto.pokemonspecies.PokemonSpeciesDto
import com.jeckonly.core_model.ui.detail.PokemonDetailUI
import com.jeckonly.core_model.ui.home.PokemonInfoUI
import java.math.RoundingMode
import java.text.DecimalFormat


fun PokemonDetailDto.toPokemonInfoUI(): PokemonInfoUI {
    return PokemonInfoUI(
        name = name,
        url = "https://pokeapi.co/api/v2/pokemon/${id}/",
        id = id
    )
}

fun getPokemonDetailUIByDto(
    pokemonDetailDto: PokemonDetailDto,
    pokemonSpeciesDto: PokemonSpeciesDto
): PokemonDetailUI {
    val femaleRate = pokemonSpeciesDto.gender_rate * 12.5
    val maleRate = 100 - femaleRate
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.CEILING
    return PokemonDetailUI(
        species = pokemonDetailDto.species.name,
        abilities = pokemonDetailDto.abilities.map {
            it.ability.name
        },
        heldItems = pokemonDetailDto.held_items.map {
            it.item.name
        },
        moves = pokemonDetailDto.moves.map {
            it.move.name
        },
        types = pokemonDetailDto.types.map {
            it.type.name
        },
        games = pokemonDetailDto.game_indices.map {
            it.version.name
        },
        stats = pokemonDetailDto.stats,
        experience = pokemonDetailDto.base_experience,
        height = (pokemonDetailDto.height * 10).toString() + "cm",
        weight = df.format(pokemonDetailDto.weight / 10) + "kg",
        shape = pokemonSpeciesDto.shape.name,
        captureRate = pokemonSpeciesDto.capture_rate,
        eggGroups = pokemonSpeciesDto.egg_groups.map {
            it.name
        },
        malePercent = df.format(maleRate)+"%",
        femalePercent = df.format(femaleRate)+"%",
        habitat = pokemonSpeciesDto.habitat.name
        )
}

fun main() {
    val femaleRate = 60
    val maleRate = 100 - femaleRate
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.CEILING
    println(df.format(maleRate))
    println(df.format(femaleRate))
}