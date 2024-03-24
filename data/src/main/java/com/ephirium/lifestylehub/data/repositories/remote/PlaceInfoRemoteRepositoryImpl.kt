package com.ephirium.lifestylehub.data.repositories.remote

import com.ephirium.lifestylehub.api.placedetails.service.PlaceDetailsService
import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.map
import com.ephirium.lifestylehub.data.mappers.toPlaceInfo
import com.ephirium.lifestylehub.domain.model.placeInfo.PlaceInfo
import com.ephirium.lifestylehub.domain.repositories.remote.PlaceInfoRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PlaceInfoRemoteRepositoryImpl(private val placesDetailsService: PlaceDetailsService) :
    PlaceInfoRemoteRepository {
    override suspend fun getPlaceInfo(
        id: String,
        languageCode: String,
    ): Flow<ResponseResult<PlaceInfo>> = flow {
        emit(placesDetailsService.getPlaceDetails(id, languageCode)
            .map { placeDetails -> placeDetails.toPlaceInfo(languageCode) })
    }.flowOn(Dispatchers.IO)
}