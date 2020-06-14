package com.challange.hilt.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.challange.hilt.MainCoroutineRule
import com.challange.hilt.TestBase
import com.challange.hilt.observeForTesting
import com.challange.hilt.ui.models.Result
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class MainViewModelTest : TestBase() {
    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repoMock: MainRepository

    lateinit var underTest: MainViewModel


    @Test
    fun `should retrieve data once view model is init`() {
        val stubData = MockData.mockData().response

        runBlockingTest {
            whenever(repoMock.getChallenge()).thenReturn(Result.Success(stubData))
            underTest = MainViewModel(repoMock)

            verify(repoMock).getChallenge()
        }
        underTest.uiData.observeForTesting {
            // Force a refresh to show the loading indicator
            assertEquals(underTest.uiData.value, stubData)
        }
    }
}

