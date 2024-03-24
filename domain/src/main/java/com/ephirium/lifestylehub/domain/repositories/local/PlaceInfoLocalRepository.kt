package com.ephirium.lifestylehub.domain.repositories.local

import com.ephirium.lifestylehub.domain.model.placeInfo.PlaceInfo
import kotlinx.coroutines.flow.Flow

interface PlaceInfoLocalRepository {
    suspend fun getPlaceInfo(id: String, languageCode: String): Flow<PlaceInfo?>
    
    suspend fun postPlaceInfo(placeInfo: PlaceInfo): Flow<Unit>
}