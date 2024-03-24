package com.ephirium.lifestylehub.domain.repositories.remote

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.domain.model.placeInfo.PlaceInfo
import kotlinx.coroutines.flow.Flow

interface PlaceInfoRemoteRepository {
    suspend fun getPlaceInfo(id: String, languageCode: String): Flow<ResponseResult<PlaceInfo>>
}