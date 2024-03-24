package com.ephirium.lifestylehub.database.placeinfo.datastore

import com.ephirium.lifestylehub.database.placeinfo.model.PlaceInfoEntity
import kotlinx.coroutines.flow.Flow

interface PlaceInfoDataStore {
    suspend fun getPlaceInfo(id: String, languageCode: String): Flow<PlaceInfoEntity?>
    
    suspend fun setPlaceInfo(placeInfo: PlaceInfoEntity): Flow<Unit>
}