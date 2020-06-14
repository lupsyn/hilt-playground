package com.challange.hilt.ui.main

import com.challange.hilt.ui.models.Challenge
import com.challange.hilt.ui.models.ChallengeResponse
import com.challange.hilt.ui.models.ChallengeType
import com.challange.hilt.ui.models.Result
import com.challange.hilt.util.wrapEspressoIdlingResource

class MainRepositoryImpl : MainRepository {
    override suspend fun getChallenge(): Result<List<Challenge>> {
        wrapEspressoIdlingResource {
            return Result.Success(mockData().response)
        }
    }

    private fun mockData(): ChallengeResponse {
        val challenges = listOf(
            Challenge(
                1,
                ChallengeType.Photo,
                "Take a picture of yourself playing one of the ps4 games!",
                "Let's play! ps4 Booth",
                125
            ),
            Challenge(
                2,
                ChallengeType.List,
                "Name this movie mash up poster",
                "Mash UP movie Poster",
                150
            ),
            Challenge(
                3,
                ChallengeType.Location,
                "Find a Droid and take a selfie with it!",
                "Star wars droid selfie",
                160
            ),
            Challenge(
                4,
                ChallengeType.Location,
                "Look up way up!",
                "Tower Check In",
                130
            ),
            Challenge(
                2,
                ChallengeType.List,
                "Name this movie mash up poster",
                "Mash UP movie Poster",
                150
            ),
            Challenge(
                2,
                ChallengeType.List,
                "Name this movie mash up poster",
                "Mash UP movie Poster",
                150
            )
        )

        return ChallengeResponse(challenges)
    }
}