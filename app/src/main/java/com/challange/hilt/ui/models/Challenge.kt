package com.challange.hilt.ui.models

data class Challenge(
    val type: ChallengeType,
    val desc: String?,
    val title: String,
    val points: Int
)

sealed class ChallengeType {
    object Location : ChallengeType()
    object Photo : ChallengeType()
    object List : ChallengeType()
}