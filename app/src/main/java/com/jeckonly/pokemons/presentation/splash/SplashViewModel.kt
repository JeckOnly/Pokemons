package com.jeckonly.pokemons.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.pokemons.ui_event.splash.SplashEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : ViewModel() {
    fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.LoadImage -> {
                viewModelScope.launch {
                    // fake loading
                    delay(3000)
                    event.finishCallback()
                }
            }
        }
    }
}