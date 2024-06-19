package com.melvin.compass

import com.melvin.compass.main.domain.WordCount
import com.melvin.compass.main.presentation.viewmodel.MainState

val wordCountList = listOf(
    WordCount("This", 1),
    WordCount("ThisThis", 1),
)

val contentString = "This ThisThis"

val tenthCharacterText = "T"

val stateMock = MainState(
    tenthCharacterText = tenthCharacterText,
    wordCounterMap = wordCountList
)