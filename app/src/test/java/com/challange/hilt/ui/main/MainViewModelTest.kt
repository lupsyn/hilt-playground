package com.challange.hilt.ui.main

import com.challange.hilt.TestBase
import com.challange.hilt.ui.models.Challenge
import org.junit.Test
import org.mockito.Mock

class MainViewModelTest : TestBase() {
    @Mock
    lateinit var repoMock: MainRepository

    @Mock
    lateinit var data: List<Challenge>

    val underTest = MainViewModel(repoMock)


    @Test
    fun `should retrieve data once view model is init`() {

    }

}