package com.melvin.compass.main.presentation.viewmodel

sealed interface MainEvent {
    data object ClickLoadData: MainEvent
}