package com.melvin.compass.main.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.melvin.compass.R
import com.melvin.compass.main.presentation.viewmodel.MainEvent
import com.melvin.compass.main.presentation.viewmodel.MainState

@Composable
fun MainScreen(
    state: MainState,
    onEvent: (MainEvent) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = state.compassContent)
            Button(onClick = { onEvent(MainEvent.ClickLoadData) }) {
                Text(text = stringResource(R.string.load_compass_data))
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(
        state = MainState(
            compassContent = "Hello, World!"
        ),
        onEvent = {}
    )
}