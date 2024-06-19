package com.melvin.compass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.melvin.compass.main.presentation.MainScreen
import com.melvin.compass.main.presentation.viewmodel.MainViewModel
import com.melvin.compass.ui.theme.CompassTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompassTheme {
                val viewModel: MainViewModel = hiltViewModel()
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                MainScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}