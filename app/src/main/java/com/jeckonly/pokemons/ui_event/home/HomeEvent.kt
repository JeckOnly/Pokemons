package com.jeckonly.pokemons.ui_event.home

sealed interface HomeEvent {
    object WantMoreItem: HomeEvent
    object ClickDownload: HomeEvent
}