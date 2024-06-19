package com.melvin.compass.main.presentation.viewmodel

data class MainState(
    val compassContent: String = "",
    val errorMessage: String = "",
    val isLoading: Boolean = false
)
