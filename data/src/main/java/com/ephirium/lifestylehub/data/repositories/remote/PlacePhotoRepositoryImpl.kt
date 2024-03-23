package com.ephirium.lifestylehub.data.repositories.remote

import com.ephirium.lifestylehub.api.places.service.PlacePhotoService
import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.domain.repositories.remote.PlacePhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class PlacePhotoRepositoryImpl(private val placePhotoService: PlacePhotoService) :
    PlacePhotoRepository {
    override suspend fun getPhotoUrls(id: String): Flow<ResponseResult<List<String>>> = flow {
        emit(placePhotoService.getPhotos(id))
    }.flowOn(Dispatchers.IO)
}