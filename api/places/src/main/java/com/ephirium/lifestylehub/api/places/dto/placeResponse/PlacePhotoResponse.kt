package com.ephirium.lifestylehub.api.places.dto.placeResponse

import kotlinx.serialization.Serializable

@Serializable
data class PlacePhotoResponse(val id: String, val prefix: String, val suffix: String)