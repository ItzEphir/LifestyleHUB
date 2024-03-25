package com.ephirium.lifestylehub.domain.repositories.remote

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.domain.model.recommendation.Place
import kotlinx.coroutines.flow.Flow

interface PlacesRemoteRepository {
    
    suspend fun getPlacesPage(
        latitude: Float,
        longitude: Float,
        page: String?,
        perPage: Int = 10,
        languageCode: String,
    ): Flow<ResponseResult<List<Place>>>
}