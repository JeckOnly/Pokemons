package com.jeckonly.pokemons.util

import androidx.core.graphics.ColorUtils

object ColorContrastUtil {

    private const val COLOR_CONTRAST_THRESHOLD = 1.5f

    fun isColorContrastOk(foregroundColor: Int, backGroundColor: Int): Boolean {
        val contrast = ColorUtils.calculateContrast(foregroundColor, backGroundColor)
        return contrast > COLOR_CONTRAST_THRESHOLD
    }
}