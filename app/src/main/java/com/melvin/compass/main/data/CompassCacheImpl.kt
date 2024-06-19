package com.melvin.compass.main.data


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.melvin.compass.main.domain.CompassCache
import com.melvin.compass.main.domain.WordCount
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = CompassCacheImpl.DATA_STORE_NAME)

class CompassCacheImpl @Inject constructor(
    private val context: Context
) : CompassCache {

    override suspend fun getTenthCharacterText(): String {
        val preferenceKey = stringPreferencesKey(KEY_TENTH_CHARACTER)
        val tenthCharacterText = context.dataStore.data
            .map {
                it[preferenceKey] ?: ""
            }
        return tenthCharacterText.first()
    }

    override suspend fun getWordCounterMap(): List<WordCount> {
        val preferenceKey = stringPreferencesKey(KEY_WORD_COUNTER)
        val wordCounterMap = context.dataStore.data
            .map {
                it[preferenceKey] ?: ""
            }
        return Json.decodeFromString<List<WordCount>>(wordCounterMap.first())
    }

    override suspend fun setTenthCharacterText(tenthCharacterText: String) {
        val preferenceKey = stringPreferencesKey(KEY_TENTH_CHARACTER)
        context.dataStore.edit { settings ->
            settings[preferenceKey] = tenthCharacterText
        }
    }

    override suspend fun setWordCounterMap(wordCounterMap: List<WordCount>) {
        val preferenceKey = stringPreferencesKey(KEY_WORD_COUNTER)
        val mapString = Json.encodeToString(wordCounterMap)
        context.dataStore.edit { settings ->
            settings[preferenceKey] = mapString
        }
    }

    companion object {
        const val DATA_STORE_NAME = "compass_cache"
        private const val KEY_TENTH_CHARACTER = "tenth_character"
        private const val KEY_WORD_COUNTER = "word_counter"
    }
}