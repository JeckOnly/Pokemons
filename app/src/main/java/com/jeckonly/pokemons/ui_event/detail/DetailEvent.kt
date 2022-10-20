package com.jeckonly.pokemons.ui_event.detail

sealed interface DetailEvent{
    class Init(val name: String): DetailEvent
}