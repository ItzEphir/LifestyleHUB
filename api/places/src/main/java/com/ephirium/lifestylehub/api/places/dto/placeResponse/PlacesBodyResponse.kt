package com.ephirium.lifestylehub.api.places.dto.placeResponse

import kotlinx.serialization.Serializable

@Serializable
data class PlacesBodyResponse(val results: List<PlaceDto>)
