package com.challange.hilt.ui.main

import com.challange.hilt.ui.models.Challenge

interface MainRepository {
    fun getChallenge(): List<Challenge>
}