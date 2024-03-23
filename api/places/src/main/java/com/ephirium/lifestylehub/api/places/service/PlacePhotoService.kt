package com.ephirium.lifestylehub.api.places.service

import com.ephirium.lifestylehub.common.ResponseResult

interface PlacePhotoService {
    
    suspend fun getPhotos(id: String): ResponseResult<List<String>>
}