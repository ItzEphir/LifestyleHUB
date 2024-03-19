package com.ephirium.lifestylehub.weather.service

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.weather.dto.reverseGeoResponse.ReverseGeoResponse

interface GeoService {
    suspend fun getCityName(lat: Float, lon: Float): ResponseResult<ReverseGeoResponse>
}