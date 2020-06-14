package com.challange.hilt.ui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Challenge(
    val id: Int,
    val type: ChallengeType,
    val desc: String?,
    val title: String,
    val points: Int
) : Parcelable


sealed class ChallengeType : Parcelable {
    @Parcelize
    object Location : ChallengeType()

    @Parcelize
    object Photo : ChallengeType()

    @Parcelize
    object List : ChallengeType()
}
