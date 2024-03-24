package com.ephirium.lifestylehub.api.placedetails.service

import com.ephirium.lifestylehub.api.placedetails.dto.PlaceDetails
import com.ephirium.lifestylehub.common.ResponseResult

interface PlaceDetailsService {
    suspend fun getPlaceDetails(id: String, languageCode: String) : ResponseResult<PlaceDetails>
}