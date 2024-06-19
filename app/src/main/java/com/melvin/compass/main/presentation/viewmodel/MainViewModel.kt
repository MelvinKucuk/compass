package com.melvin.compass.main.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.compass.main.domain.CompassCache
import com.melvin.compass.main.domain.CompassRepository
import com.melvin.compass.main.domain.countWordOccurrences
import com.melvin.compass.main.domain.generateTenthString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CompassRepository,
    private val compassCache: CompassCache
): ViewModel() {

    private val _uiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.ClickLoadData -> {
                loadData()
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val result = repository.getCompassContent()
            result?.let {
                val contentString = result.string()

                val tenthCharacter = withContext(Dispatchers.IO) {
                    contentString.generateTenthString()
                }

                compassCache.setTenthCharacterText(tenthCharacter)

                val wordCounterMap = withContext(Dispatchers.IO) {
                    contentString.countWordOccurrences()
                }

                compassCache.setWordCounterMap(wordCounterMap)

                _uiState.update {
                    it.copy(
                        tenthCharacterText = tenthCharacter,
                        wordCounterMap = wordCounterMap
                    )
                }
            } ?: run {
                val tenthCharacter = compassCache.getTenthCharacterText()
                val wordCounterMap = compassCache.getWordCounterMap()
                if (tenthCharacter.isNotEmpty() && wordCounterMap.isNotEmpty()) {
                    _uiState.update {
                        it.copy(
                            tenthCharacterText = tenthCharacter,
                            wordCounterMap = wordCounterMap
                        )
                    }
                }
            }
        }
    }
}