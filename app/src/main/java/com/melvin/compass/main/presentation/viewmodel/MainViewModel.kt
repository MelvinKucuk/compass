package com.melvin.compass.main.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.compass.main.domain.CompassRepository
import com.melvin.compass.main.domain.countWordOccurrences
import com.melvin.compass.main.domain.generateTenthString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CompassRepository
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
            val tenthResult = async { repository.getCompassContent() }.await()
            val wordCountResult = async { repository.getCompassContent() }.await()

            tenthResult?.let {
                val contentString = it.string()
                _uiState.update { state ->
                    state.copy(
                        tenthCharacterText = contentString.generateTenthString(),
                    )
                }
            }

            wordCountResult?.let {
                val contentString = it.string()
                _uiState.update { state ->
                    state.copy(
                        wordCounterMap = contentString.countWordOccurrences()
                    )
                }
            }
        }
    }
}