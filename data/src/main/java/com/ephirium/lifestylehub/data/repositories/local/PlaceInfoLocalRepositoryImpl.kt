package com.ephirium.lifestylehub.data.repositories.local

import com.ephirium.lifestylehub.data.mappers.toEntity
import com.ephirium.lifestylehub.data.mappers.toPlaceInfo
import com.ephirium.lifestylehub.database.placeinfo.datastore.PlaceInfoDataStore
import com.ephirium.lifestylehub.domain.model.placeInfo.PlaceInfo
import com.ephirium.lifestylehub.domain.repositories.local.PlaceInfoLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaceInfoLocalRepositoryImpl(private val placeInfoDataStore: PlaceInfoDataStore) :
    PlaceInfoLocalRepository {
    override suspend fun getPlaceInfo(id: String, languageCode: String): Flow<PlaceInfo?> =
        placeInfoDataStore.getPlaceInfo(id, languageCode).map {
            it?.toPlaceInfo()
        }
    
    override suspend fun postPlaceInfo(placeInfo: PlaceInfo): Flow<Unit> =
        placeInfoDataStore.setPlaceInfo(placeInfo.toEntity())
    
}