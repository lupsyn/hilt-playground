package com.challange.hilt.ui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChallengeResponse(val response: List<Challenge>) : Parcelable