package com.jeckonly.pokemons.presentation.detail.pagerscreen

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.jeckonly.core_model.ui.detail.PokemonDetailUI
import com.jeckonly.pokemons.design.component.pokemon.PokemonDetailItem
import com.jeckonly.pokemons.design.theme.*

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerScope.StatsPager(
    pageIndex: Int,
    pokemonDetailUI: PokemonDetailUI,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val statsUiList: List<StatsUI> by remember(pokemonDetailUI) {
        if (pokemonDetailUI.captureRate != 0) {
            val list: MutableList<StatsUI> = mutableListOf()
            pokemonDetailUI.stats.forEach {
                list.add(
                    StatsUI(
                        statsName = it.stat.name,
                        maxValue = 300f,
                        minValue = 0f,
                        currentValue = it.base_stat.toFloat()
                    )
                )
            }
            list.add(
                StatsUI(
                    statsName = "capture rate",
                    maxValue = 255f,
                    minValue = 1f,
                    currentValue = pokemonDetailUI.captureRate.toFloat()
                )
            )
            mutableStateOf(list)
        } else {
            mutableStateOf(emptyList())
        }
    }


    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        for (statsUI in statsUiList) {
            PokemonDetailItem(
                itemName = statsUI.statsNameShort, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                val progress: Float by animateFloatAsState(
                    targetValue = if (currentPage == pageIndex) statsUI.progressPercent else 0f,
                    animationSpec = TweenSpec(durationMillis = 600, easing = FastOutSlowInEasing)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = statsUI.currentValue.toInt().toString(),
                        fontSize = 16.sp,
                        color = Blue10,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    LinearProgressIndicator(
                        progress = progress,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(30))
                            .height(9.dp),
                        color = statsUI.color,
                        backgroundColor = statsUI.color.copy(alpha = 0.1f)
                    )
                }
            }
        }
    }
}

data class StatsUI(
    val statsName: String,
    val maxValue: Float,
    val minValue: Float,
    val currentValue: Float,
) {
    val color = getBackgroundColorByStatsName(statsName)
    val statsNameShort = getStatsNameShort(statsName)
    val progressPercent = currentValue / maxValue


    private fun getBackgroundColorByStatsName(name: String): Color {
        return when (name) {
            "hp" -> Hp
            "attack" -> Attack
            "defense" -> Defense
            "special-attack" -> SpecialAttack
            "special-defense" -> SpecialDefense
            "speed" -> Speed
            "capture rate" -> CaptureRate
            else -> Color.Black
        }
    }

    private fun getStatsNameShort(name: String): String {
        return when (name) {
            "hp" -> "hp"
            "attack" -> "attack"
            "defense" -> "defense"
            "special-attack" -> "sp-attack"
            "special-defense" -> "sp-defence"
            "speed" -> "speed"
            "capture rate" -> "capture rate"
            else -> "null"
        }
    }
}