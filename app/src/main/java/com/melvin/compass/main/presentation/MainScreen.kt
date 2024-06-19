package com.melvin.compass.main.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = stringResource(R.string.tenth_character),
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = state.tenthCharacterText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.word_count),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            items(state.wordCounterMap) {
                Row {
                    Text(
                        text = it.word,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = it.count.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }

            item {
                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = { onEvent(MainEvent.ClickLoadData) }) {
                    Text(text = stringResource(R.string.load_compass_data))
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(
        state = MainState(
            tenthCharacterText = "Hello, World!"
        ),
        onEvent = {}
    )
}