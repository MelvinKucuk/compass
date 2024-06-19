package com.melvin.compass.main.presentation.viewmodel

import com.melvin.compass.main.domain.WordCount

data class MainState(
    val tenthCharacterText: String = "",
    val wordCounterMap: List<WordCount> = listOf(),
    val errorMessage: String = "",
    val isLoading: Boolean = false
)
