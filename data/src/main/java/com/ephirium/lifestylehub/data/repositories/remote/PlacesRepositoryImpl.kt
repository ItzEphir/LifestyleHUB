package com.ephirium.lifestylehub.data.repositories.remote

import com.ephirium.lifestylehub.api.places.service.PlacePhotoService
import com.ephirium.lifestylehub.api.places.service.PlaceService
import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.ResponseResult.Ok
import com.ephirium.lifestylehub.common.map
import com.ephirium.lifestylehub.common.onOk
import com.ephirium.lifestylehub.data.mappers.toPlace
import com.ephirium.lifestylehub.data.utils.getCursorFromUrl
import com.ephirium.lifestylehub.domain.model.recommendation.Place
import com.ephirium.lifestylehub.domain.repositories.remote.PlacesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class PlacesRepositoryImpl(
    private val placeService: PlaceService,
    private val placePhotoService: PlacePhotoService,
) : PlacesRepository {
    
    override suspend fun getPlacesPage(
        latitude: Float,
        longitude: Float,
        page: String?,
        perPage: Int,
        languageCode: String,
    ): Flow<ResponseResult<List<Place>>> = flow {
        emit(placeService.getPlacesPage(latitude, longitude, page, perPage, languageCode))
    }.map { responseResult ->
        responseResult.map { placesResponse ->
            placesResponse.bodyResponse.results.map { placeDto ->
                placeDto.toPlace(
                    placesResponse.headerResponse.link?.getCursorFromUrl(),
                    when (val photoResult = placePhotoService.getPhotos(placeDto.id)) {
                        is Ok -> photoResult.data
                        
                        else  -> null
                    }
                )
            }
        }
    }.flowOn(Dispatchers.IO)
}