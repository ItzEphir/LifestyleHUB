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
    
    private var nextUrl: String? = null
    
    override suspend fun getPlaces(
        latitude: Float,
        longitude: Float,
    ): Flow<ResponseResult<List<Place>>> = flow {
        emit(placeService.getPlaces(latitude, longitude).onOk {
            nextUrl = it.headerResponse.link
        })
    }.map { responseResult ->
        responseResult.map { placesResponse ->
            placesResponse.bodyResponse.results.map { it.toPlace(placesResponse.headerResponse.link?.getCursorFromUrl(), null) }
        }
    }.flowOn(Dispatchers.IO)
    
    override suspend fun getPlacesPage(
        latitude: Float,
        longitude: Float,
        page: String?,
        perPage: Int,
    ): Flow<ResponseResult<List<Place>>> = flow {
        emit(placeService.getPlacesPage(latitude, longitude, page, perPage))
    }.map { responseResult ->
        responseResult.map { placesResponse ->
            placesResponse.bodyResponse.results.map { placeDto ->
                when (val photoResult = placePhotoService.getPhotos(placeDto.id)) {
                    is Ok -> placeDto.toPlace(
                        placesResponse.headerResponse.link?.getCursorFromUrl().also { println("repository: $it") }, photoResult.data
                    )
                    
                    else  -> placeDto.toPlace(
                        placesResponse.headerResponse.link?.getCursorFromUrl(), null
                    )
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}