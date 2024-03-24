package com.ephirium.lifestylehub.database.placeinfo.datastore

import com.ephirium.lifestylehub.database.placeinfo.PlaceInfoDatabase
import com.ephirium.lifestylehub.database.placeinfo.model.PlaceInfoEntity
import io.realm.kotlin.ext.asFlow
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

internal class PlaceInfoDataStoreImpl : PlaceInfoDataStore {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getPlaceInfo(id: String, languageCode: String): Flow<PlaceInfoEntity?> =
        PlaceInfoDatabase.realm.query<PlaceInfoEntity>().asFlow().mapLatest { resultsChange ->
            resultsChange.list.lastOrNull { entity -> entity._id == id && entity.languageCode == languageCode }
        }.flowOn(Dispatchers.IO)
    
    override suspend fun setPlaceInfo(placeInfo: PlaceInfoEntity): Flow<Unit> =
        PlaceInfoDatabase.realm.write {
            copyToRealm(placeInfo)
        }.asFlow().map { }.flowOn(Dispatchers.IO)
}