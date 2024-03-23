package com.ephirium.lifestylehub.domain.repositories.remote

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.domain.model.recommendation.Place
import kotlinx.coroutines.flow.Flow

interface PlacesRepository {
    suspend fun getPlaces(latitude: Float, longitude: Float): Flow<ResponseResult<List<Place>>>
    
    suspend fun getPlacesPage(
        latitude: Float,
        longitude: Float,
        page: String?,
        perPage: Int = 10,
    ): Flow<ResponseResult<List<Place>>>
}