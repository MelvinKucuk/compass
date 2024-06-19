package com.melvin.compass.main.domain

interface CompassCache {
    suspend fun getTenthCharacterText(): String
    suspend fun getWordCounterMap(): List<WordCount>
    suspend fun setTenthCharacterText(tenthCharacterText: String)
    suspend fun setWordCounterMap(wordCounterMap: List<WordCount>)
}