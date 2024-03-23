package com.ephirium.lifestylehub.api.places.dto.placeResponse

import kotlinx.serialization.Serializable

@Serializable
data class PlacesResponse(
    val headerResponse: PlacesHeaderResponse,
    val bodyResponse: PlacesBodyResponse
)
