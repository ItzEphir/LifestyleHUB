package com.ephirium.lifestylehub.domain.usecases

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.map
import com.ephirium.lifestylehub.domain.model.recommendation.Recommendation
import com.ephirium.lifestylehub.domain.repositories.remote.PlacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRecommendationsUseCase(
    private val placesRepository: PlacesRepository
) {
    
    suspend operator fun invoke(
        latitude: Float,
        longitude: Float,
        languageCode: String,
        page: String? = null,
        perPage: Int = 10,
    ): Flow<ResponseResult<List<Recommendation>>> =
        placesRepository.getPlacesPage(latitude, longitude, page, perPage, languageCode).map { responseResult ->
            responseResult.map { places ->
                places.map { place ->
                    place
                }
            }
        }
}