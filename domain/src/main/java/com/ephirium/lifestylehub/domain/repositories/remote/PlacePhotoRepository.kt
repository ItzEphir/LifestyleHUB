package com.ephirium.lifestylehub.domain.repositories.remote

import com.ephirium.lifestylehub.common.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PlacePhotoRepository {
    suspend fun getPhotoUrls(id: String): Flow<ResponseResult<List<String>>>
}