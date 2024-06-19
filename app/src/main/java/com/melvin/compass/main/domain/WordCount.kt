package com.melvin.compass.main.domain

import kotlinx.serialization.Serializable

@Serializable
data class WordCount(
    val word: String,
    val count: Int
)
