package com.melvin.compass.main.presentation.viewmodel

import com.melvin.compass.MainCoroutineRule
import com.melvin.compass.contentString
import com.melvin.compass.main.domain.CompassCache
import com.melvin.compass.main.domain.CompassRepository
import com.melvin.compass.stateMock
import com.melvin.compass.tenthCharacterText
import com.melvin.compass.wordCountList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val repository: CompassRepository = mockk(relaxed = true)

    private val compassCache: CompassCache = mockk(relaxed = true)

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(
            repository = repository,
            compassCache = compassCache,
            dispatcherIO = coroutineRule.dispatcher
        )
    }

    @Test
    fun `loadData retrieves compass content and updates UI state`() = runTest {
        val mockCompassContent = contentString.toResponseBody()
        coEvery { repository.getCompassContent() } returns mockCompassContent
        coEvery { compassCache.getTenthCharacterText() } returns contentString
        coEvery { compassCache.getWordCounterMap() } returns wordCountList

        viewModel.onEvent(MainEvent.ClickLoadData)

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        coVerify { repository.getCompassContent() }
        coVerify { compassCache.setTenthCharacterText(any()) }
        coVerify { compassCache.setWordCounterMap(any()) }
        assertEquals(stateMock, viewModel.uiState.value)
    }

    @Test
    fun `loadData retrieves cached data when repository returns null`() = runTest {
        coEvery { repository.getCompassContent() } returns null
        coEvery { compassCache.getTenthCharacterText() } returns tenthCharacterText
        coEvery { compassCache.getWordCounterMap() } returns wordCountList

        viewModel.onEvent(MainEvent.ClickLoadData)

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        coVerify { repository.getCompassContent() }
        coVerify { compassCache.getTenthCharacterText() }
        coVerify { compassCache.getWordCounterMap() }
        assertEquals(stateMock, viewModel.uiState.value)
    }

    @Test
    fun `loadData does not update UI state when cached data is empty`() = runTest {
        coEvery { repository.getCompassContent() } returns null
        coEvery { compassCache.getTenthCharacterText() } returns ""
        coEvery { compassCache.getWordCounterMap() } returns listOf()

        viewModel.onEvent(MainEvent.ClickLoadData)

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        coVerify { repository.getCompassContent() }
        coVerify { compassCache.getTenthCharacterText() }
        coVerify { compassCache.getWordCounterMap() }
        assertEquals(MainState(), viewModel.uiState.value)
    }

}