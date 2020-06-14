package com.challange.hilt.ui.main

import com.challange.hilt.ui.models.Challenge
import com.challange.hilt.ui.models.Result

interface MainRepository {
    suspend fun getChallenge(): Result<List<Challenge>>
}