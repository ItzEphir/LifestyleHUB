package com.ephirium.lifestylehub.api.places.service

import com.ephirium.lifestylehub.api.places.dto.placeResponse.PlacesResponse
import com.ephirium.lifestylehub.common.ResponseResult

interface PlaceService {
    suspend fun getPlaces(lat: Float, lon: Float, limit: Int = 10, languageCode: String): ResponseResult<PlacesResponse>
    
    suspend fun getPlacesPage(
        lat: Float,
        lon: Float,
        page: String?,
        perPage: Int = 10,
        languageCode: String,
    ): ResponseResult<PlacesResponse>
}