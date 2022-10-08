package com.jeckonly.pokemons.ui_event.splash

sealed interface SplashEvent {
    class LoadImage(val finishCallback: () -> Unit): SplashEvent
}